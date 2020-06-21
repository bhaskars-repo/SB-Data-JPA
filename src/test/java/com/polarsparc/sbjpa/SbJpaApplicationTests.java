/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa;

import com.polarsparc.sbjpa.model.Location;
import com.polarsparc.sbjpa.repository.LocationRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SbJpaApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(SbJpaApplicationTests.class);

    @Autowired
    private LocationRepository repository;

    @BeforeAll
    public void insertDummies() {
        log.info("Ready to insert a test Locations ...");

        repository.save(new Location("City One", "Maine", "00001"));
        repository.save(new Location("City One", "Delaware", "00002"));
        repository.save(new Location("City Two", "California", "00003"));
        repository.save(new Location("City Three", "Maine", "00004"));

        log.info("Successfully inserted test Locations !!!");
    }

    @AfterAll
    public void deleteDummies() {
        log.info("Ready to find all dummy Locations ...");

        Location loc1 = repository.findByZip("00001").get();
        Location loc2 = repository.findByZip("00002").get();
        Location loc3 = repository.findByZip("00003").get();
        Location loc4 = repository.findByZip("00004").get();

        log.info("Ready to delete all dummy Locations ...");

        repository.delete(loc1);
        repository.delete(loc2);
        repository.delete(loc3);
        repository.delete(loc4);
    }

    @Test
    public void testFindLocationByZip() {
        Assertions.assertTrue(repository.findByZip("00001").isPresent());
    }

    @Test
    public void testFindLocationByCity() {
        Assertions.assertEquals(repository.findByCity("City One").size(), 2);
    }

    @Test
    public void testFindLocationByState() {
        Assertions.assertEquals(repository.findByState("Maine").size(), 2);
    }

    @Test
    public void testFindLocationByCitState() {
        Assertions.assertEquals(repository.findByCityAndState("City Two", "California").get().getState(),
                "California");
    }

    @Test
    public void testExceptionDuplicateCityState() {
        log.info("Ready to insert duplicate city/state Location ...");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(new Location("City One", "Maine", "00005"));
        });
    }

    @Test
    public void testExceptionDuplicateZip() {
        log.info("Ready to insert duplicate zip Location ...");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(new Location("City Four", "Maine", "00001"));
        });
    }

}
