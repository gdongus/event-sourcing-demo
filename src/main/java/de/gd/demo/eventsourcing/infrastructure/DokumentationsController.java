package de.gd.demo.eventsourcing.infrastructure;

import org.springframework.web.bind.annotation.RestController;

import de.gd.demo.eventsourcing.commands.EmpfangeErstdokumentation;

@RestController
public class DokumentationsController {
    
    void createEvent(EmpfangeErstdokumentation empfangeErstdokumentation) {
        // create event
    }
}
