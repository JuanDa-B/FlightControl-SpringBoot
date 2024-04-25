/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service.impl;

import com.app.rest.entity.Flight;
import com.app.rest.persistence.IFlightDAO;
import com.app.rest.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements IFlightService {

    private final IFlightDAO flightDAO;

    @Autowired
    public FlightServiceImpl(IFlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }


    @Override
    public List<Flight> findAll() {
        return flightDAO.findAll();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightDAO.findById(id);
    }

    @Override
    public void save(Flight flight) {
        flightDAO.save(flight);
    }

    @Override
    public void deleteById(Long id) {
        flightDAO.deleteById(id);
    }
}
