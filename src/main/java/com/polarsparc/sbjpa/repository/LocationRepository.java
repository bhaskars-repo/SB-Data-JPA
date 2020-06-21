/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.repository;

import com.polarsparc.sbjpa.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByCity(String city);

    List<Location> findByState(String state);

    Optional<Location> findByCityAndState(String city, String state);

    Optional<Location> findByZip(String zip);
}
