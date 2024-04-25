/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.controller;


import com.app.rest.controller.dto.ReservationDTO;
import com.app.rest.entity.Reservation;
import com.app.rest.service.IReservationService;
import com.app.rest.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private ISeatService seatService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Reservation> productOptional = reservationService.findById(id);

        if (productOptional.isPresent()) {
            Reservation reservation = productOptional.get();
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .id(reservation.getId())
                    .reservationDate(reservation.getReservationDate())
                    .passenger(reservation.getPassenger())
                    .flight(reservation.getFlight())
                    .seat(reservation.getSeat())
                    .build();

            return ResponseEntity.ok(reservationDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find/FyS/{fid}/{sid}")
    public ResponseEntity<Object> findById(@PathVariable Long fid, @PathVariable Long sid) {
        Optional<Reservation> productOptional = reservationService.findByFlightIdAndSeatId(fid, sid);

        if (productOptional.isPresent()) {
            Reservation reservation = productOptional.get();
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .id(reservation.getId())
                    .reservationDate(reservation.getReservationDate())
                    .passenger(reservation.getPassenger())
                    .flight(reservation.getFlight())
                    .seat(reservation.getSeat())
                    .build();

            return ResponseEntity.ok(reservationDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        List<ReservationDTO> reservationDTOList = reservationService.findAll()
                .stream().map(reservation -> ReservationDTO.builder()
                        .id(reservation.getId())
                        .reservationDate(reservation.getReservationDate())
                        .passenger(reservation.getPassenger())
                        .flight(reservation.getFlight())
                        .seat(reservation.getSeat())
                        .build()).toList();

        return ResponseEntity.ok(reservationDTOList);
    }

    @GetMapping("/findAll/flight/{id}")
    public ResponseEntity<Object> findAllByFlight(@PathVariable Long id) {

        List<ReservationDTO> reservationDTOList = reservationService.findAllByFlightId(id)
                .stream().map(reservation -> ReservationDTO.builder()
                        .id(reservation.getId())
                        .reservationDate(reservation.getReservationDate())
                        .passenger(reservation.getPassenger())
                        .flight(reservation.getFlight())
                        .seat(reservation.getSeat())
                        .build()).toList();

        return ResponseEntity.ok(reservationDTOList);
    }


    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ReservationDTO reservationDTO) throws URISyntaxException {

        if (reservationDTO.getFlight() == null || reservationDTO.getPassenger() == null || reservationDTO.getSeat() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Reservation> reservationOptional = reservationService.findByFlightIdAndSeatId(reservationDTO.getFlight().getId(), reservationDTO.getSeat().getId());

        if (reservationOptional.isPresent()) {
            return ResponseEntity.badRequest().body("El asiento ya se encuentra reservado para este vuelo");
        }

        Reservation reservation = Reservation.builder()
                .reservationDate(reservationDTO.getReservationDate())
                .passenger(reservationDTO.getPassenger())
                .flight(reservationDTO.getFlight())
                .seat(reservationDTO.getSeat())
                .build();


        reservationService.save(reservation);


        return ResponseEntity.created(new URI("/api/reservation/")).build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {

        Optional<Reservation> productOptional = reservationService.findById(id);

        if (productOptional.isPresent()) {
            Reservation reservation = productOptional.get();
            reservation.setReservationDate(reservationDTO.getReservationDate());
            reservation.setPassenger(reservationDTO.getPassenger());
            reservation.setFlight(reservationDTO.getFlight());
            reservation.setSeat(reservationDTO.getSeat());

            reservationService.save(reservation);

            return ResponseEntity.ok("Registro Actualizado");

        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {

        if (id != null) {
            reservationService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado");
        }

        return ResponseEntity.badRequest().build();

    }

}

