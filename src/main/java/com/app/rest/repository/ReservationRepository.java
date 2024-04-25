/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.repository;


import com.app.rest.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findAllByFlightId(Long flightId);
    Optional<Reservation> findByFlightIdAndSeatId (Long flightId, Long seatId);

    List<Reservation> findAllByPassengerDocument(Long document);

}