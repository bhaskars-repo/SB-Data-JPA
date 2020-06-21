/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.component;

import com.polarsparc.sbjpa.model.Course;
import com.polarsparc.sbjpa.model.CourseLevel;
import com.polarsparc.sbjpa.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoursesLoader {

    private static final Logger log = LoggerFactory.getLogger(CoursesLoader.class);

    @Bean
    public ApplicationRunner loadCourses(CourseRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Ready to insert Course records ...");

                repository.save(new Course("AL001", "Introduction to Algorithms",
                        CourseLevel.BEGINNER));
                repository.save(new Course("AZ001", "Azure Cloud Fundamentals",
                        CourseLevel.BEGINNER));
                repository.save(new Course("AZ002", "Azure Networking",
                        CourseLevel.INTERMEDIATE));
                repository.save(new Course("DP001", "Data Structures in Python",
                        CourseLevel.INTERMEDIATE));
                repository.save(new Course("DG001", "Distributed Algorithms in Go",
                        CourseLevel.ADVANCED));

                log.info("Completed inserting Course records !!!");
            }

            log.info("Ready to display all Course records ...");

            repository.findAll().forEach(course -> log.info(course.toString()));

            log.info("Ready to display Course record for course code AZ002 ...");

            repository.findByCourseCode("AZ002").ifPresent(course -> log.info(course.toString()));
        };
    }
}
