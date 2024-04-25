/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.controller.dto;

import com.app.rest.entity.Flight;
import com.app.rest.entity.Seat;
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
public class AirplaneDTO {

    private Long id;
    private String plate;
    private String company;
    private int businessCapacity;
    private int economyCapacity;

    private List<Flight> flight;
    private List<Seat> seats = new ArrayList<>();

}
