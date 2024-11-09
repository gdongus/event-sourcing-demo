package de.gd.demo.eventsourcing.events.types.domain;

import java.time.LocalDate;

import de.gd.demo.eventsourcing.events.types.EventHandle;

public class ErstDokumentationEmpfangen extends DomainEvent {

    private final LocalDate unterschriftsDatum;

    public ErstDokumentationEmpfangen(
            LocalDate unterschriftsDatum,
            String aggregateId,
            Long aggregateVersion,
            String eventVersion) {

        super(aggregateId, aggregateVersion);
        this.unterschriftsDatum = unterschriftsDatum;
    }

    public LocalDate getUnterschriftsDatum() {
        return unterschriftsDatum;
    }

    @Override
    public EventHandle getEventHandle() {
        return EventHandle.ErstDokumentationEmpfangen;
    }
}
