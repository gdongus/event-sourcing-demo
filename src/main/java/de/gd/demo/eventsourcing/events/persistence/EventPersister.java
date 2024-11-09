package de.gd.demo.eventsourcing.events.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.gd.demo.eventsourcing.events.types.AggregateHandle;
import de.gd.demo.eventsourcing.events.types.ShellEvent;
import de.gd.demo.eventsourcing.events.types.domain.DomainEvent;

public class EventPersister {

    Logger LOG = LoggerFactory.getLogger(EventPersister.class);

    private EventRepository eventRepository;

    private ObjectMapper objectMapper;

    public EventPersister(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
    }

    public void persistEvents(List<DomainEvent> events) {
        eventRepository.save(
                events.stream()
                        .map(domainEvent -> {
                            try {
                                return new ShellEvent(
                                        domainEvent.getAggregateId(),
                                        domainEvent.getAggregateVersion(),
                                        AggregateHandle.Dokumentation,
                                        0,
                                        domainEvent.getEventHandle(),
                                        objectMapper.writeValueAsString(domainEvent));
                            } catch (JsonProcessingException e) {
                                LOG.error("Error (JsonProcessingException) while persisting event");
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList())

        );
    }
}
