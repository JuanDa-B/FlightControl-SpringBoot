/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service;

import com.app.rest.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    List<Reservation> findAllByFlightId(Long flightId);
    Optional<Reservation> findByFlightIdAndSeatId (Long flightId, Long seatId);
    void save(Reservation reservation);
    void deleteById(Long id);

}
