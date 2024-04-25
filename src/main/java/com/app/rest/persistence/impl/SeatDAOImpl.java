/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.persistence.impl;

import com.app.rest.entity.Seat;
import com.app.rest.persistence.ISeatDAO;
import com.app.rest.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SeatDAOImpl implements ISeatDAO {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatDAOImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAll() {
        return (List<Seat>) seatRepository.findAll();
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Optional<Seat> findByLocation(String location) {
        return seatRepository.findByLocation(location);
    }

    @Override
    public void save(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public void deleteById(Long id) {
        seatRepository.deleteById(id);
    }

}
