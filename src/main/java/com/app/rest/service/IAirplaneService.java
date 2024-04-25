/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service;

import com.app.rest.entity.Airplane;

import java.util.List;
import java.util.Optional;

public interface IAirplaneService {

    List<Airplane> findAll();
    Optional<Airplane> findById(Long id);
    Optional<Airplane> findByPlate(String plate);
    void save(Airplane airplane);
    void deleteById(Long id);

    boolean existsByPlate(String plate);

}
