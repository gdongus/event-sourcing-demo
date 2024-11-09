package de.gd.demo.eventsourcing.events.types;

public record ShellEvent(
                String aggregateId,
                Long aggregateVersion,
                AggregateHandle aggregateHandle,
                int eventVersion,
                EventHandle eventHandle,
                String domainEventJson) {
}