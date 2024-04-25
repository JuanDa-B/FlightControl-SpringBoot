/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service.impl;

import com.app.rest.entity.Seat;
import com.app.rest.persistence.ISeatDAO;
import com.app.rest.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements ISeatService {

    private final ISeatDAO seatDAO;

    @Autowired
    public SeatServiceImpl(ISeatDAO seatDAO) {
        this.seatDAO = seatDAO;
    }

    @Override
    public List<Seat> findAll() {
        return seatDAO.findAll();
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatDAO.findById(id);
    }

    @Override
    public Optional<Seat> findByLocation(String location) {
        return seatDAO.findByLocation(location);
    }

    @Override
    public void save(Seat seat) {
        seatDAO.save(seat);
    }

    @Override
    public void deleteById(Long id) {
        seatDAO.deleteById(id);
    }



}
