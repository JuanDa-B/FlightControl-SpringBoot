/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/



package com.app.rest.controller.dto;

import com.app.rest.entity.Airplane;
import com.app.rest.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatDTO {

    private Long id;
    private String location;

    private Airplane airplane;

    private String seatClass;
    private String seatType;

    private List<Reservation> reservationList = new ArrayList<>();
}
