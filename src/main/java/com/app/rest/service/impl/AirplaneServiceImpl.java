/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service.impl;

import com.app.rest.entity.Airplane;
import com.app.rest.persistence.IAirplaneDAO;
import com.app.rest.service.IAirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneServiceImpl implements IAirplaneService {


    private final IAirplaneDAO airplaneDAO;

    @Autowired
    public AirplaneServiceImpl(IAirplaneDAO airplaneDAO) {
        this.airplaneDAO = airplaneDAO;
    }

    @Override
    public List<Airplane> findAll() {
        return airplaneDAO.findAll();
    }

    @Override
    public Optional<Airplane> findById(Long id) {
        return airplaneDAO.findById(id);
    }

    @Override
    public Optional<Airplane> findByPlate(String plate) {
        return airplaneDAO.findByPlate(plate);
    }

    @Override
    public void save(Airplane airplane) {
        airplaneDAO.save(airplane);
    }

    @Override
    public void deleteById(Long id) {
        airplaneDAO.deleteById(id);
    }

    @Override
    public boolean existsByPlate(String plate) {
        return airplaneDAO.existsByPlate(plate);
    }
}
