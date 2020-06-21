/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.component;

import com.polarsparc.sbjpa.model.Member;
import com.polarsparc.sbjpa.repository.LocationRepository;
import com.polarsparc.sbjpa.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

@Configuration
public class MembersLoader {

    private static final Logger log = LoggerFactory.getLogger(MembersLoader.class);

    private final LocationRepository locationRepository;

    public MembersLoader(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Bean
    public ApplicationRunner loadMembers(MemberRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Ready to insert Member records ...");

                locationRepository.findByCityAndState("Brooklyn", "New York").ifPresent(location -> {
                    repository.save(new Member("alice.blue@earth.co", "Alice", "Blue",
                            new Date(System.currentTimeMillis()), location));
                });

                locationRepository.findByCityAndState("Herndon", "Virginia").ifPresent(location -> {
                    repository.save(new Member("bob.b@space.in", "Bob", "Brown",
                            new Date(System.currentTimeMillis()), location));
                });

                locationRepository.findByCityAndState("Fairfax", "Virginia").ifPresent(location -> {
                    repository.save(new Member("c_green@mount.net", "Charlie", "Green",
                            new Date(System.currentTimeMillis()), location));
                });

                locationRepository.findByCityAndState("Edison", "New Jersey").ifPresent(location ->
                    repository.save(new Member("dave_blue@fire.org", "David", "Blue",
                            new Date(System.currentTimeMillis()), location)));

                locationRepository.findByCityAndState("Brooklyn", "New York").ifPresent(location ->
                    repository.save(new Member("eva_m@drive.com", "Eva", "Maroon",
                            new Date(System.currentTimeMillis()), location)));

                log.info("Completed inserting Member records !!!");
            }

            log.info("Ready to display all Member records ...");

            repository.findAll().forEach(member -> log.info(member.toString()));

            log.info("Ready to display Member record with emailId c_green@mount.net ...");

            repository.findByEmailId("c_green@mount.net").ifPresent(member -> log.info(member.toString()));

            log.info("Ready to display Member records with last name Blue ...");

            repository.findByLastName("Blue").forEach(member -> log.info(member.toString()));

            log.info("Ready to display Member records located in Brooklyn/New York ...");

            locationRepository.findByCityAndState("Brooklyn", "New York").ifPresent(location ->
                    repository.findByLocation(location).forEach(member -> log.info(member.toString())));
        };
    }

}
