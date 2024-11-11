package de.gd.demo.eventsourcing.infrastructure;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.gd.demo.eventsourcing.commands.CommandHandler;
import de.gd.demo.eventsourcing.commands.EmpfangeErstdokumentation;
import de.gd.demo.eventsourcing.events.persistence.EventReader;
import de.gd.demo.eventsourcing.events.types.domain.DomainEvent;

@RestController
public class DokumentationsController {

    private CommandHandler commandHandler;

    private EventReader eventReader;

    public DokumentationsController(CommandHandler commandHandler, EventReader eventReader) {
        this.commandHandler = commandHandler;
        this.eventReader = eventReader;
    }

    @PostMapping("/events")
    public void createEvent(@RequestBody EmpfangeErstdokumentation empfangeErstdokumentation) {
        commandHandler.handleCommand(empfangeErstdokumentation);
    }

    @GetMapping("/events")
    public List<DomainEvent> getEvents() {
        return eventReader.getEvents();
    }
}
