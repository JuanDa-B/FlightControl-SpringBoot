/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/


package com.app.rest.controller;


import com.app.rest.controller.dto.FlightDTO;
import com.app.rest.entity.Flight;
import com.app.rest.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/flight")
@CrossOrigin("*")
public class FlightController {

    private final IFlightService flightService;

    @Autowired
    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Flight> flightOptional = flightService.findById(id);

        return getObjectResponseEntity(flightOptional);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Optional<Flight> flightOptional) {
        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();

            FlightDTO flightDTO = FlightDTO.builder()
                    .id(flight.getId())
                    .departure(flight.getDeparture())
                    .arrival(flight.getArrival())
                    .date(flight.getDate())
                    .departureTime(flight.getDepartureTime())
                    .duration(flight.getDuration())
                    .airplane(flight.getAirplane())
                    .reservationList(flight.getReservationList())
                    .build();

            return ResponseEntity.ok(flightDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        List<FlightDTO> flightList = flightService.findAll()
                .stream()
                .map(flight -> FlightDTO.builder()
                        .id(flight.getId())
                        .departure(flight.getDeparture())
                        .arrival(flight.getArrival())
                        .date(flight.getDate())
                        .departureTime(flight.getDepartureTime())
                        .duration(flight.getDuration())
                        .airplane(flight.getAirplane())
                        .reservationList(flight.getReservationList())
                        .build())
                .toList();

        return ResponseEntity.ok(flightList);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody FlightDTO flightDTO) throws URISyntaxException {

        if (flightDTO.getDeparture().isBlank() || flightDTO.getArrival().isBlank() || flightDTO.getDate() == null || flightDTO.getDepartureTime() == null || flightDTO.getDuration() == null || flightDTO.getAirplane() == null) {
            return ResponseEntity.badRequest().build();
        }

        flightService.save(Flight.builder()
                .departure(flightDTO.getDeparture())
                .arrival(flightDTO.getArrival())
                .date(flightDTO.getDate())
                .departureTime(flightDTO.getDepartureTime())
                .duration(flightDTO.getDuration())
                .airplane(flightDTO.getAirplane())
                .build());

        return ResponseEntity.created(new URI("/api/flight/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {

        Optional<Flight> flightOptional = flightService.findById(id);

        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            flight.setDeparture(flightDTO.getDeparture());
            flight.setArrival(flightDTO.getArrival());
            flight.setDate(flightDTO.getDate());
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setDuration(flightDTO.getDuration());
            flight.setAirplane(flightDTO.getAirplane());
            flightService.save(flight);

            return ResponseEntity.ok("Registro Actualizado");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {

        if (flightService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (id != null) {
            flightService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }


}
