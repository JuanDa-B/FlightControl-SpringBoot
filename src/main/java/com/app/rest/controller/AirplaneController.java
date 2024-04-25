/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.controller;


import com.app.rest.controller.dto.AirplaneDTO;
import com.app.rest.entity.Airplane;
import com.app.rest.service.IAirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airplane")
@CrossOrigin("*")
public class AirplaneController {

    private final IAirplaneService airplaneService;

    @Autowired
    public AirplaneController(IAirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Airplane> airplaneOptional = airplaneService.findById(id);

        return getObjectResponseEntity(airplaneOptional);
    }

    @GetMapping("/find/plate/{plate}")
    public ResponseEntity<Object> findByPlate(@PathVariable String plate) {
        Optional<Airplane> airplaneOptional = airplaneService.findByPlate(plate);

        return getObjectResponseEntity(airplaneOptional);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Optional<Airplane> airplaneOptional) {
        if (airplaneOptional.isPresent()) {
            Airplane airplane = airplaneOptional.get();

            AirplaneDTO airplaneDTO = AirplaneDTO.builder()
                    .id(airplane.getId())
                    .plate(airplane.getPlate())
                    .company(airplane.getCompany())
                    .businessCapacity(airplane.getBusinessCapacity())
                    .economyCapacity(airplane.getEconomyCapacity())
                    .flight(airplane.getFlight())
                    .seats(airplane.getSeats())
                    .build();

            return ResponseEntity.ok(airplaneDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        List<AirplaneDTO> airplaneList = airplaneService.findAll()
                .stream()
                .map(airplane -> AirplaneDTO.builder()
                        .id(airplane.getId())
                        .plate(airplane.getPlate())
                        .company(airplane.getCompany())
                        .businessCapacity(airplane.getBusinessCapacity())
                        .economyCapacity(airplane.getEconomyCapacity())
                        .flight(airplane.getFlight())
                        .seats(airplane.getSeats())
                        .build())
                .toList();

        return ResponseEntity.ok(airplaneList);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody AirplaneDTO airplaneDTO) throws URISyntaxException {

        if (airplaneDTO.getCompany().isBlank() || airplaneDTO.getPlate().isBlank() || airplaneDTO.getBusinessCapacity() == 0 || airplaneDTO.getEconomyCapacity() == 0) {
            return ResponseEntity.badRequest().build();
        }

        if(airplaneService.existsByPlate(airplaneDTO.getPlate())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La placa ya se encuentra Registrada");
        }

        airplaneService.save(Airplane.builder()
                .plate(airplaneDTO.getPlate())
                .company(airplaneDTO.getCompany())
                .businessCapacity(airplaneDTO.getBusinessCapacity())
                .economyCapacity(airplaneDTO.getEconomyCapacity())
                .build());

        return ResponseEntity.created(new URI("/api/airplane/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAirplane(@PathVariable Long id, @RequestBody AirplaneDTO airplaneDTO) {

        Optional<Airplane> airplaneOptional = airplaneService.findById(id);

        if (airplaneOptional.isPresent()) {
            Airplane airplane = airplaneOptional.get();
            airplane.setPlate(airplaneDTO.getPlate());
            airplane.setCompany(airplaneDTO.getCompany());
            airplane.setBusinessCapacity(airplaneDTO.getBusinessCapacity());
            airplane.setEconomyCapacity(airplaneDTO.getEconomyCapacity());
            airplaneService.save(airplane);

            return ResponseEntity.ok("Registro Actualizado");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {

        if (airplaneService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (id != null) {
            airplaneService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }



}
