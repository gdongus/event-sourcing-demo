package de.gd.demo.eventsourcing.infrastructure;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.gd.demo.eventsourcing.commands.CommandHandler;
import de.gd.demo.eventsourcing.commands.EmpfangeErstdokumentation;

@RestController
public class DokumentationsController {

    private CommandHandler commandHandler;

    public DokumentationsController(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/events")
    public void createEvent(@RequestBody EmpfangeErstdokumentation empfangeErstdokumentation) {
        commandHandler.handleCommand(empfangeErstdokumentation);
    }
}
