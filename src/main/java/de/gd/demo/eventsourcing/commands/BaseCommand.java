package de.gd.demo.eventsourcing.commands;

public interface BaseCommand {
    String getAggregateId();
}
