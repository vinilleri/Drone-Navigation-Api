package com.api.drone.model;

import com.api.drone.enums.Acao;
import com.api.drone.enums.Direcao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovimentoDTO{

    private Direcao orientacao;
    private Acao acao;
}

