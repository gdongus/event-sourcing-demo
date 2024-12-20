package de.gd.demo.eventsourcing.events.persistence;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.gd.demo.eventsourcing.events.types.EventHandle;
import de.gd.demo.eventsourcing.events.types.ShellEvent;
import de.gd.demo.eventsourcing.events.types.domain.DomainEvent;
import de.gd.demo.eventsourcing.events.types.domain.ErstDokumentationEmpfangen;
import de.gd.demo.eventsourcing.events.types.domain.FolgedokumentationEmpfangenEvent;

@Service
public class EventReader {

    private static Logger LOG = LoggerFactory.getLogger(EventReader.class);

    private EventRepository repository;

    private ObjectMapper objectMapper;

    public EventReader(EventRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public List<DomainEvent> getEvents() {
        return repository.findAll().stream()
                .map(shellevent -> extractDomainEventFromShell(shellevent))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<DomainEvent> getEvents(String aggregateId) {
        return repository.findByAggregateId(aggregateId).stream()
                .map(shellevent -> extractDomainEventFromShell(shellevent))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private DomainEvent extractDomainEventFromShell(ShellEvent shellevent) {
        switch (shellevent.eventHandle()) {
            case EventHandle.FolgeDokumentationEmpfangen -> {
                try {
                    return objectMapper.readValue(shellevent.domainEventJson(), FolgedokumentationEmpfangenEvent.class);
                } catch (JsonProcessingException e) {
                    LOG.error("Failed to parse event" + shellevent.toString(), e);
                    return null;
                }
            }
            case EventHandle.ErstDokumentationEmpfangen -> {
                try {
                    return objectMapper.readValue(shellevent.domainEventJson(),
                            ErstDokumentationEmpfangen.class);
                } catch (JsonProcessingException e) {
                    LOG.error("Failed to parse event" + shellevent.toString(), e);
                    return null;
                }
            }

            default -> throw new IllegalArgumentException("Unknown event type: " + shellevent.toString());
        }
    }
}