/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.service;

import com.app.rest.entity.Passenger;

import java.util.List;
import java.util.Optional;

public interface IPassengerService {
    List<Passenger> findAll();
    Optional<Passenger> findById(Long id);
    Optional<Passenger> findByDocument(Long document);
    void save(Passenger passenger);
    void deleteById(Long id);

    boolean existsByDocument(Long document);

}
