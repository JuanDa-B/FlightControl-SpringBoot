/*

@Author: Juan David Beltran Piza
@University: Universidad ECCI
@Subject: Pruebas de Software 4ANS
@Year: 2024-1

*/

package com.app.rest.repository;

import com.app.rest.entity.Airplane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, Long> {

    Optional<Airplane> findByPlate(String plate);
    boolean existsByPlate(String plate);

}
