package com.stormlabs.shackcontrol.server.domain.esp8266;

import com.stormlabs.shackcontrol.server.domain.circuit.SwitchState;
import com.stormlabs.shackcontrol.server.domain.greeting.Greeting;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Log
public class ESP8266Controller {
    private SimpMessagingTemplate template;

    @Autowired
    public ESP8266Controller(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendUpdate(SwitchState switchState) {
        template.convertAndSend("/topic/homecontrol", switchState);
    }

    @MessageMapping("/greeting")
    @SendTo("/topic/homecontrol")
    public String greeting(Greeting greeting) {
        return greeting.getName() + " just joined the application";
    }


}
