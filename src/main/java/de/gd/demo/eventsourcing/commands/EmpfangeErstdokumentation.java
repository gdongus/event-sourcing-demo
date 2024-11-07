package de.gd.demo.eventsourcing.commands;

import java.time.LocalDate;

import de.gd.demo.eventsourcing.events.types.AggregateHandle;

public class EmpfangeErstdokumentation implements BaseCommand {
    private String aggregateId;
    private AggregateHandle aggregateHandle;
    private LocalDate unterschiftsDatum;

    public AggregateHandle getAggregateHandle() {
        return aggregateHandle;
    }

    public LocalDate getUnterschiftsDatum() {
        return unterschiftsDatum;
    }

    @Override
    public String getAggregateId() {
        return aggregateId;
    }
}