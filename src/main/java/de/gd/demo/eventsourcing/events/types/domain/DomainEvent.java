package de.gd.demo.eventsourcing.events.types.domain;

public class DomainEvent {
    private String aggregateId;
    private Long aggregateVersion;

    public DomainEvent(String aggregateId, Long aggregateVersion) {
        this.aggregateId = aggregateId;
        this.aggregateVersion = aggregateVersion;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
