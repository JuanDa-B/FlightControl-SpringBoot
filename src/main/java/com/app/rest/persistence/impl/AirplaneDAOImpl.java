/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/


package com.app.rest.persistence.impl;

import com.app.rest.entity.Airplane;
import com.app.rest.persistence.IAirplaneDAO;
import com.app.rest.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AirplaneDAOImpl implements IAirplaneDAO {

    private final AirplaneRepository airplaneRepository;

    @Autowired
    public AirplaneDAOImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public List<Airplane> findAll() {
        return (List<Airplane>) airplaneRepository.findAll();
    }

    @Override
    public Optional<Airplane> findById(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public Optional<Airplane> findByPlate(String plate) {
        return airplaneRepository.findByPlate(plate);
    }

    @Override
    public void save(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    @Override
    public void deleteById(Long id) {
        airplaneRepository.deleteById(id);
    }

    @Override
    public boolean existsByPlate(String plate) {
        return airplaneRepository.existsByPlate(plate);
    }
}
