package com.api.drone.model;


import com.api.drone.enums.Tipo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Posicao {

    private int x;
    private int y;
    private Tipo tipo;

}
