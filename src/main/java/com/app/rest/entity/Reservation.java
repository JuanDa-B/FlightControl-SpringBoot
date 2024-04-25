/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/



package com.app.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reserva")
    private LocalDate reservationDate; // Cambio de Date a LocalDate

    @ManyToOne
    @JoinColumn(name = "id_pasajero", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "id_asiento", nullable = false)
    private Seat seat;


}
