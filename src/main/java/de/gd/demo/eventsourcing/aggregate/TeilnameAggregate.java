package de.gd.demo.eventsourcing.aggregate;

import java.util.List;

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

    public void apply(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            apply(event);
        }
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

    /**
     * Used to avoid race conditions. The version is incremented with each event!
     * 
     * @return Long
     */
    public Long getVersion() {
        return this.version;
    }
}
