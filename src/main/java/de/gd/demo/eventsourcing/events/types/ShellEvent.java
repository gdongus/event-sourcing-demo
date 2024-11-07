package de.gd.demo.eventsourcing.events.types;

public record ShellEvent(
        String aggregateId,
        int aggregateVersion,
        AggregateHandle aggregateHandle,
        String eventVersion,
        EventHandle eventHandle,
        String domainEventJson) {
}