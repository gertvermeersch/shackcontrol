package com.stormlabs.shackcontrol.server.domain.circuit;

import lombok.*;

@Data
@AllArgsConstructor
public class SwitchState {
    private Integer id;
    private Boolean state;
}
