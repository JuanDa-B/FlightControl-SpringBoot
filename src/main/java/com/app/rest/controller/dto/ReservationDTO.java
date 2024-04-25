/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/


package com.app.rest.controller.dto;

import com.app.rest.entity.Flight;
import com.app.rest.entity.Passenger;
import com.app.rest.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {

    private Long id;
    private LocalDate reservationDate; // Cambio de Date a LocalDate

    private Passenger passenger;
    private Flight flight;
    private Seat seat;

}
