
package de.gd.demo.eventsourcing;

import java.util.List;

import de.gd.demo.eventsourcing.aggregate.TeilnameAggregate;
import de.gd.demo.eventsourcing.commands.BaseCommand;
import de.gd.demo.eventsourcing.commands.EmpfangeErstdokumentation;
import de.gd.demo.eventsourcing.commands.EmpfangeFolgedokumentationimplements;
import de.gd.demo.eventsourcing.events.persistence.EventReader;
import de.gd.demo.eventsourcing.events.types.domain.DomainEvent;
import de.gd.demo.eventsourcing.events.types.domain.ErstDokumentationEmpfangen;

public class CommandHandler {

    private EventReader eventReader;

    public CommandHandler(EventReader eventReader) {
        this.eventReader = eventReader;
    }

    public List<DomainEvent> handle(BaseCommand command) {
      return  switch (command) {
        case EmpfangeErstdokumentation command -> {
            TeilnameAggregate aggregate = rebuildAggregate(command.getAggregateId());
            if  (aggregate.getErstdokumentation() == null) {

                List.of(new ErstDokumentationEmpfangen(
                    command.getUnterschiftsDatum(),
                     command.getAggregateId(), aggregate.getVersion(), null));
            }

            
        }
        case EmpfangeFolgedokumentationimplements command -> {

        }
        default -> Collections.emptyList();
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
