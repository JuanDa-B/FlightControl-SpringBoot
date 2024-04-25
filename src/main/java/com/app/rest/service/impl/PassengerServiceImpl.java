/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service.impl;

import com.app.rest.entity.Passenger;
import com.app.rest.persistence.IPassengerDAO;
import com.app.rest.service.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements IPassengerService {

    private final IPassengerDAO passengerDAO;

    @Autowired
    public PassengerServiceImpl(IPassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Override
    public List<Passenger> findAll() {
        return passengerDAO.findAll();
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        return passengerDAO.findById(id);
    }

    @Override
    public Optional<Passenger> findByDocument(Long document){
        return passengerDAO.findByDocument(document);
    }

    @Override
    public void save(Passenger passenger) {
        passengerDAO.save(passenger);
    }

    @Override
    public void deleteById(Long id) {
        passengerDAO.deleteById(id);
    }

    @Override
    public boolean existsByDocument(Long document) {
        return passengerDAO.existsByDocument(document);
    }
}
