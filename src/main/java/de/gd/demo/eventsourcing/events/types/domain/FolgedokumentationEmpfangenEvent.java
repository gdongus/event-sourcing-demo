package de.gd.demo.eventsourcing.events.types.domain;

import java.time.LocalDate;

public class FolgedokumentationEmpfangenEvent extends DomainEvent {
 
    private final LocalDate unterschriftsDatum;


    public FolgedokumentationEmpfangenEvent(LocalDate unterschriftsDatum, String aggregateId, String eventVersion) {
        super(aggregateId);
        this.unterschriftsDatum = unterschriftsDatum;
    }

    public LocalDate getUnterschriftsDatum() {
        return unterschriftsDatum;
    }
}
