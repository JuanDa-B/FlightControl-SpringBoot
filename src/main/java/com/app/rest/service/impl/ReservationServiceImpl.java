/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service.impl;

import com.app.rest.entity.Reservation;
import com.app.rest.persistence.IReservationDAO;
import com.app.rest.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationDAO reservationDAO;

    @Autowired
    public ReservationServiceImpl(IReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationDAO.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationDAO.findById(id);
    }

    @Override
    public List<Reservation> findAllByFlightId(Long flightId) {
        return reservationDAO.findAllByFlightId(flightId);
    }

    @Override
    public Optional<Reservation> findByFlightIdAndSeatId(Long flightId, Long seatId) {
        return reservationDAO.findByFlightIdAndSeatId(flightId,seatId);
    }


    @Override
    public void save(Reservation reservation) {
        reservationDAO.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationDAO.deleteById(id);
    }


}
