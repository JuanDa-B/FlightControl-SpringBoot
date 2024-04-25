/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/


package com.app.rest.controller.dto;

import com.app.rest.entity.Reservation;
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
public class PassengerDTO {


    private Long id;
    private Long document;
    private String name;

    private List<Reservation> reservationList = new ArrayList<>();

}
