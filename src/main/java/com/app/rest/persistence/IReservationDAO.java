/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.persistence;

import com.app.rest.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationDAO {

    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    void save(Reservation reservation);
    List<Reservation> findAllByFlightId(Long flightId);
    Optional<Reservation> findByFlightIdAndSeatId (Long flightId, Long seatId);
    void deleteById(Long id);
}
