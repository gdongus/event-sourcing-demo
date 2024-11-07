package de.gd.demo.eventsourcing.events.persistence;

import de.gd.demo.eventsourcing.events.types.ShellEvent;

public class EventPersister {
    private EventRepository eventRepository;

    public EventPersister(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void persistEvent(ShellEvent event) {
        eventRepository.save(event);
    }
}
