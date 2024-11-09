
package de.gd.demo.eventsourcing;

import java.util.Collections;
import java.util.List;

import de.gd.demo.eventsourcing.aggregate.TeilnameAggregate;
import de.gd.demo.eventsourcing.commands.BaseCommand;
import de.gd.demo.eventsourcing.commands.EmpfangeErstdokumentation;
import de.gd.demo.eventsourcing.commands.EmpfangeFolgedokumentationimplements;
import de.gd.demo.eventsourcing.events.persistence.EventPersister;
import de.gd.demo.eventsourcing.events.persistence.EventReader;
import de.gd.demo.eventsourcing.events.types.domain.DomainEvent;
import de.gd.demo.eventsourcing.events.types.domain.ErstDokumentationEmpfangen;
import de.gd.demo.eventsourcing.events.types.domain.FolgedokumentationEmpfangenEvent;

public class CommandHandler {

    private EventReader eventReader;

    private EventPersister eventPersister;

    public CommandHandler(EventReader eventReader, EventPersister eventPersister) {
        this.eventReader = eventReader;
        this.eventPersister = eventPersister;
    }

    public void handleCommand(BaseCommand command) {
        TeilnameAggregate aggregate = rebuildAggregate(command.getAggregateId());

        List<DomainEvent> events = switch (command) {
            case EmpfangeErstdokumentation erstdokumentation ->
                handleErstdokumentation(erstdokumentation, aggregate);
            case EmpfangeFolgedokumentationimplements folgeDokumentation ->
                handleFolgedokumentation(folgeDokumentation, aggregate);
            default -> Collections.emptyList();
        };

        // apply to make sure the aggregate can handle the new events and update its
        // state.
        aggregate.apply(events);
        eventPersister.persistEvents(events);
    }

    private List<DomainEvent> handleFolgedokumentation(EmpfangeFolgedokumentationimplements folgeDokumentation,
            TeilnameAggregate aggregate) {
        if (aggregate.getFolgedokumentation() == null) {
            return List.of(new FolgedokumentationEmpfangenEvent(
                    folgeDokumentation.getUnterschiftsDatum(),
                    folgeDokumentation.getAggregateId(), aggregate.getVersion(), null));
        } else {
            return Collections.emptyList();
        }
    }

    private List<DomainEvent> handleErstdokumentation(EmpfangeErstdokumentation erstdokumentation,
            TeilnameAggregate aggregate) {
        if (aggregate.getErstdokumentation() == null) {
            return List.of(new ErstDokumentationEmpfangen(
                    erstdokumentation.getUnterschiftsDatum(),
                    erstdokumentation.getAggregateId(), aggregate.getVersion(), null));
        } else {
            return Collections.emptyList();
        }
    }

    private TeilnameAggregate rebuildAggregate(String aggregateId) {
        TeilnameAggregate aggregate = new TeilnameAggregate();

        eventReader.readEvents(aggregateId)
                .stream()
                .forEach(event -> aggregate.apply(event));

        return aggregate;

    }
}
