/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.component;

import com.polarsparc.sbjpa.model.Location;
import com.polarsparc.sbjpa.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationsLoader {

    private static final Logger log = LoggerFactory.getLogger(LocationsLoader.class);

    @Bean
    public ApplicationRunner loadLocations(LocationRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Ready to insert Location records ...");

                repository.save(new Location("Lawrenceville", "New Jersey", "08648"));
                repository.save(new Location("Edison", "New Jersey", "08820"));
                repository.save(new Location("Brooklyn", "New York", "11201"));
                repository.save(new Location("Herndon", "Virginia", "2170"));
                repository.save(new Location("Fairfax", "Virginia", "22030"));
                repository.save(new Location("Lawrenceville", "Atlanta", "30046"));

                log.info("Completed inserting Location records !!!");
            }

            log.info("Ready to display all Location records ...");

            repository.findAll().forEach(location -> log.info(location.toString()));

            log.info("Ready to display Location records for city Lawrenceville ...");

            repository.findByCity("Lawrenceville").forEach(location -> log.info(location.toString()));

            log.info("Ready to display Location records for state Virginia ...");

            repository.findByState("Virginia").forEach(location -> log.info(location.toString()));

            log.info("Ready to display Location record for city Brooklyn/state New York ...");

            repository.findByCityAndState("Brooklyn", "New York").ifPresent(location ->
                    log.info(location.toString()));

            log.info("Ready to display Location record for zip 22030 ...");

            repository.findByZip("22030").ifPresent(location -> log.info(location.toString()));
        };
    }

}
