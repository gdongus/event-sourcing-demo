package de.gd.demo.eventsourcing.events.types.domain;

import de.gd.demo.eventsourcing.events.types.EventHandle;

public abstract class DomainEvent {
    private String aggregateId;
    private Long aggregateVersion;

    public Long getAggregateVersion() {
        return aggregateVersion;
    }

    public DomainEvent(String aggregateId, Long aggregateVersion) {
        this.aggregateId = aggregateId;
        this.aggregateVersion = aggregateVersion;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public abstract EventHandle getEventHandle();
}
