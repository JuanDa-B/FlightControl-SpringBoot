/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/


package com.app.rest.persistence.impl;

import com.app.rest.entity.Passenger;
import com.app.rest.persistence.IPassengerDAO;
import com.app.rest.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PassengerDAOImpl implements IPassengerDAO {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerDAOImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<Passenger> findAll() {
        return (List<Passenger>) passengerRepository.findAll();
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        return passengerRepository.findById(id);
    }

    @Override
    public Optional<Passenger> findByDocument(Long document){
        return passengerRepository.findByDocument(document);
    }

    @Override
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public boolean existsByDocument(Long document) {
        return passengerRepository.existsByDocument(document);
    }
}
