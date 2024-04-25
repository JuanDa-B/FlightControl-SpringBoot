/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/


package com.app.rest.persistence;

import com.app.rest.entity.Flight;
import com.app.rest.entity.Passenger;

import java.util.List;
import java.util.Optional;

public interface IFlightDAO {

    List<Flight> findAll();
    Optional<Flight> findById(Long id);
    void save(Flight flight);
    void deleteById(Long id);

}
