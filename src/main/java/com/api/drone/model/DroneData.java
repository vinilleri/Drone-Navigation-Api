package com.api.drone.model;

import com.api.drone.enums.Tipo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DroneData {

    private int x;
    private int y;
    private List<Posicao> adjacentes;
    private Posicao destino;
}
