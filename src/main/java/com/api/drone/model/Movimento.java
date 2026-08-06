package com.api.drone.model;

import com.api.drone.enums.Direcao;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Movimento {
    private Direcao direcao;
}
