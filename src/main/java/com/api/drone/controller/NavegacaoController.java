package com.api.drone.controller;

import com.api.drone.enums.Direcao;
import com.api.drone.model.DroneData;
import com.api.drone.service.NavegacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/droneapi/navegacao")
@CrossOrigin("*")
public class NavegacaoController {

    private final NavegacaoService service;

    public NavegacaoController(NavegacaoService service) {
        this.service = service;
    }

    @PostMapping("/controlar-drone")
    public ResponseEntity<?> controlarDrone(@RequestBody DroneData data){

        Direcao direcao = service.movimentarDrone(data);

        return ResponseEntity.status(HttpStatus.OK).body(direcao);
    }
}
