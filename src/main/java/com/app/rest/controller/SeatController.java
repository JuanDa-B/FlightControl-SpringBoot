/*

@Author: Juan David Beltran Piza
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.controller;

import com.app.rest.controller.dto.SeatDTO;
import com.app.rest.entity.Seat;
import com.app.rest.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/seat")
@CrossOrigin("*")
public class SeatController {


    @Autowired
    private ISeatService seatService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Seat> seatOptional = seatService.findById(id);

        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            SeatDTO seatDTO = SeatDTO.builder()
                    .id(seat.getId())
                    .location(seat.getLocation())
                    .airplane(seat.getAirplane())
                    .seatClass(seat.getSeatClass())
                    .seatType(seat.getSeatType())
                    .reservationList(seat.getReservationList())
                    .build();

            return ResponseEntity.ok(seatDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll(){
        List<SeatDTO> seatDTOList = seatService.findAll()
                .stream().map(seat -> SeatDTO.builder()
                        .id(seat.getId())
                        .location(seat.getLocation())
                        .airplane(seat.getAirplane())
                        .seatClass(seat.getSeatClass())
                        .seatType(seat.getSeatType())
                        .seatClass(seat.getSeatClass())
                        .reservationList(seat.getReservationList())
                        .build()).toList();

        return ResponseEntity.ok(seatDTOList);
    }



    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody SeatDTO seatDTO) throws URISyntaxException {

        if (seatDTO.getLocation() == null || seatDTO.getAirplane() == null || seatDTO.getSeatClass() == null || seatDTO.getSeatType() == null){
            return ResponseEntity.badRequest().build();
        }

        if(seatService.findByLocation(seatDTO.getLocation()).isPresent()){
            return ResponseEntity.badRequest().body("La ubicacion ya existe");
        }

        Seat seat = Seat.builder()
                .location(seatDTO.getLocation())
                .airplane(seatDTO.getAirplane())
                .seatClass(seatDTO.getSeatClass())
                .seatType(seatDTO.getSeatType())
                .build();

        seatService.save(seat);
        return ResponseEntity.created(new URI("/api/seat/")).build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody SeatDTO seatDTO){

        Optional<Seat> seatOptional = seatService.findById(id);

        if(seatOptional.isPresent()){
            Seat seat = seatOptional.get();

            seat.setLocation(seatDTO.getLocation());
            seat.setAirplane(seatDTO.getAirplane());
            seat.setSeatClass(seatDTO.getSeatClass());
            seat.setSeatType(seatDTO.getSeatType());

            seatService.save(seat);

            return ResponseEntity.ok("Registro Actualizado");

        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){

        if(id != null){
            seatService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado");
        }

        return ResponseEntity.badRequest().build();

    }


}
