/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.persistence;

import com.app.rest.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface ISeatDAO {

    List<Seat> findAll();
    Optional<Seat> findById(Long id);
    Optional<Seat> findByLocation(String location);
    void save(Seat reservation);
    void deleteById(Long id);

}
