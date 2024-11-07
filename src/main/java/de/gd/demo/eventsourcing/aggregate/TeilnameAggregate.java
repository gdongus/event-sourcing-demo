package de.gd.demo.eventsourcing.aggregate;

import de.gd.demo.eventsourcing.events.types.domain.DomainEvent;
import de.gd.demo.eventsourcing.events.types.domain.ErstDokumentationEmpfangen;
import de.gd.demo.eventsourcing.events.types.domain.FolgedokumentationEmpfangenEvent;

public class TeilnameAggregate {

    private Long version = 0L;

    private Erstdokumentation erstdokumentation;

    private Folgedokumentation folgedokumentation;

    public Erstdokumentation getErstdokumentation() {
        return erstdokumentation;
    }

    public Folgedokumentation getFolgedokumentation() {
        return folgedokumentation;
    }

    public void apply(DomainEvent domainEvent) {
        switch (domainEvent) {
            case ErstDokumentationEmpfangen e -> {
                this.erstdokumentation = new Erstdokumentation(e.getUnterschriftsDatum());
                this.version++;
            }
            case FolgedokumentationEmpfangenEvent e -> {
                this.folgedokumentation = new Folgedokumentation(e.getUnterschriftsDatum());
                this.version++;
            }
            default -> throw new IllegalArgumentException("Unknown event type: " + domainEvent);
        }
    }

    public boolean istAktive() {
        return erstdokumentation != null && folgedokumentation != null;
    }

    public Long getVersion() {
        return this.version;
    }
}
