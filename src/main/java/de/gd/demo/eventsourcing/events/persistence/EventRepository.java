package de.gd.demo.eventsourcing.events.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.gd.demo.eventsourcing.events.types.ShellEvent;

public abstract interface EventRepository {

    static Map<String, List<ShellEvent>> storage = new HashMap<String, List<ShellEvent>>();

    default void save(ShellEvent event) {
        List<ShellEvent> list = storage.get(event.aggregateId());

        if (null == list)
            list = new ArrayList<>();

        list.add(event);
        storage.put(event.aggregateId(), list);
    }

    default List<ShellEvent> findByAggregateId(String aggregateId) {
        return storage.get(aggregateId);
    }

}
