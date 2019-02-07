package com.stormlabs.shackcontrol.server.domain.circuit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SwitchStateController {

    private SwitchStateService switchStateService;


    public SwitchStateController(SwitchStateService switchStateService) {
        this.switchStateService = switchStateService;

    }

    @GetMapping(path = "/switches")
    public ResponseEntity<List<SwitchState>> getAll() {
        return ResponseEntity.ok(switchStateService.getAll());
    }

    @GetMapping(path = "/switches/{id}")
    public ResponseEntity<SwitchState> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(switchStateService.getById(id));
    }

    @PutMapping(path = "/switches/{id}")
    public ResponseEntity<SwitchState> update(@PathVariable("id") Integer id, @RequestBody SwitchState switchState) {
        switchStateService.update(switchState, id);
        return ResponseEntity.ok().body(switchState);
    }
}
