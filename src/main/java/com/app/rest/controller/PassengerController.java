/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/


package com.app.rest.controller;

import com.app.rest.controller.dto.PassengerDTO;
import com.app.rest.entity.Passenger;
import com.app.rest.service.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passenger")
@CrossOrigin("*")
public class PassengerController {

    private final IPassengerService passengerService;

    @Autowired
    public PassengerController(IPassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Passenger> passengerOptional = passengerService.findById(id);

        return getObjectResponseEntity(passengerOptional);
    }

    @GetMapping("/find/document/{document}")
    public ResponseEntity<Object> findByDocument(@PathVariable Long document) {
        Optional<Passenger> passengerOptional = passengerService.findByDocument(document);

        return getObjectResponseEntity(passengerOptional);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Optional<Passenger> passengerOptional) {
        if (passengerOptional.isPresent()) {
            Passenger passenger = passengerOptional.get();

            PassengerDTO passengerDTO = PassengerDTO.builder()
                    .id(passenger.getId())
                    .document(passenger.getDocument())
                    .name(passenger.getName())
                    .reservationList(passenger.getReservationList())
                    .build();

            return ResponseEntity.ok(passengerDTO);
        }

        return ResponseEntity.badRequest().body("No existe el usuario");
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {

        List<PassengerDTO> passengerList = passengerService.findAll()
                .stream()
                .map(passenger -> PassengerDTO.builder()
                        .id(passenger.getId())
                        .document(passenger.getDocument())
                        .name(passenger.getName())
                        .reservationList(passenger.getReservationList())
                        .build())
                .toList();

        return ResponseEntity.ok(passengerList);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody PassengerDTO passengerDTO) throws URISyntaxException {

        if (passengerDTO.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (passengerService.existsByDocument(passengerDTO.getDocument())){
            return ResponseEntity.badRequest().body("El numero de documento ya esta registrado");
        }

        passengerService.save(Passenger.builder()
                .document(passengerDTO.getDocument())
                .name(passengerDTO.getName())
                .build());



        return ResponseEntity.created(new URI("/api/maker/save")).body(passengerService.findByDocument(passengerDTO.getDocument()).get().getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateMaker(@PathVariable Long id, @RequestBody PassengerDTO passengerDTO) {

        Optional<Passenger> passengerOptional = passengerService.findById(id);

        if (passengerOptional.isPresent()) {
            Passenger passenger = passengerOptional.get();
            passenger.setDocument(passengerDTO.getDocument());
            passenger.setName(passengerDTO.getName());
            passengerService.save(passenger);

            return ResponseEntity.ok("Registro Actualizado");
        }

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {

        if (passengerService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (id != null) {
            passengerService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }


        return ResponseEntity.badRequest().build();
    }


}
