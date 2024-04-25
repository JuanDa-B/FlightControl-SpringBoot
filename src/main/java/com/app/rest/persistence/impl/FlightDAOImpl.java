/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/



package com.app.rest.persistence.impl;

import com.app.rest.entity.Flight;
import com.app.rest.persistence.IFlightDAO;
import com.app.rest.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FlightDAOImpl implements IFlightDAO {


    private final FlightRepository flightRepository;

    @Autowired
    public FlightDAOImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> findAll() {
        return (List<Flight>) flightRepository.findAll();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }
}
