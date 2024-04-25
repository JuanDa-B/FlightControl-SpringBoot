/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.repository;

import com.app.rest.entity.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {

    Optional<Passenger> findByDocument(Long document);
    boolean existsByDocument(Long document);

}
