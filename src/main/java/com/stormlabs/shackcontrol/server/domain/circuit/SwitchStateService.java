package com.stormlabs.shackcontrol.server.domain.circuit;

import com.stormlabs.shackcontrol.server.domain.esp8266.ESP8266Controller;
import com.stormlabs.shackcontrol.server.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SwitchStateService {
    private HashMap<Integer, SwitchState> switches;
    private ESP8266Controller esp8266Controller;



    public SwitchStateService(ESP8266Controller esp8266Controller) {
        this.esp8266Controller = esp8266Controller;
        switches = new HashMap<>();
        switches.put(1, new SwitchState(1, false));
        switches.put(2, new SwitchState(2, false));
        switches.put(3, new SwitchState(3, false));
        switches.put(4, new SwitchState(4, false));
        switches.put(5, new SwitchState(5, false));
        switches.put(6, new SwitchState(6, false));
    }



    public List<SwitchState> getAll () {
        ArrayList<SwitchState> allSwitchStates = new ArrayList<>();
        switches.forEach((k,v)-> allSwitchStates.add(v));
        return allSwitchStates;
    }

    public SwitchState getById(Integer id) {
        if(!switches.containsKey(id)) {
            throw new EntityNotFoundException();
        }
        return switches.get(id);
    }

    public SwitchState update(SwitchState switchState, Integer id) {
        if(!switches.containsKey(id)) {
            throw new EntityNotFoundException();
        } else {
            //alert the websocket
            switchState.setId(id);
            esp8266Controller.sendUpdate(switchState);
            this.switches.put(switchState.getId(), switchState);
        }
        return switchState;
    }
}
