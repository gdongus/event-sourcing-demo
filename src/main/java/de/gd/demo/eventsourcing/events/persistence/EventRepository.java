package de.gd.demo.eventsourcing.events.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import de.gd.demo.eventsourcing.events.types.ShellEvent;

@Service
public class EventRepository {

    static Map<String, List<ShellEvent>> storage = new HashMap<String, List<ShellEvent>>();

    public void save(List<ShellEvent> events) {
        if (events == null || events.size() == 0)
            return;

        String aggregateId = events.get(0).aggregateId();
        List<ShellEvent> list = storage.getOrDefault(aggregateId, new ArrayList<>());

        list.addAll(events);
        storage.put(aggregateId, list);
    }

    public List<ShellEvent> findByAggregateId(String aggregateId) {
        return storage.getOrDefault(aggregateId, Collections.emptyList());
    }

    public List<ShellEvent> findAll() {
        List<ShellEvent> allEvents = new ArrayList<>();
        storage.values().forEach(allEvents::addAll);

        return allEvents;
    }
}
