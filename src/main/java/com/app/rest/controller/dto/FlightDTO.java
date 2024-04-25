/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.controller.dto;

import com.app.rest.entity.Airplane;
import com.app.rest.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDTO {

    private Long id;
    private String departure;
    private String arrival;
    private LocalDate date;
    private LocalTime departureTime;
    private BigDecimal duration;

    private Airplane airplane;
    private List<Reservation> reservationList = new ArrayList<>();

}
