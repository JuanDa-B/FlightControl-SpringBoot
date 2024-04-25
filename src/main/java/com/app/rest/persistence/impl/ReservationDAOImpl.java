/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.persistence.impl;

import com.app.rest.entity.Reservation;
import com.app.rest.persistence.IReservationDAO;
import com.app.rest.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservationDAOImpl implements IReservationDAO {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationDAOImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAllByFlightId(Long flightId) {
        return reservationRepository.findAllByFlightId(flightId);
    }

    @Override
    public Optional<Reservation> findByFlightIdAndSeatId(Long flightId, Long seatId) {
        return reservationRepository.findByFlightIdAndSeatId(flightId, seatId);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
