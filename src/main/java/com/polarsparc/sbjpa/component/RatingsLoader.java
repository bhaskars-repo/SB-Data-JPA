/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.component;

import com.polarsparc.sbjpa.model.Rating;
import com.polarsparc.sbjpa.repository.CourseRepository;
import com.polarsparc.sbjpa.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RatingsLoader {

    private static final Logger log = LoggerFactory.getLogger(RatingsLoader.class);

    private final CourseRepository courseRepository;

    public RatingsLoader(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Bean
    public ApplicationRunner loadCourseRatings(RatingRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Ready to insert Rating records ...");

                courseRepository.findByCourseCode("AZ001").ifPresent(course -> {
                    repository.save(new Rating("It was a good intro to Azure", 3, course));
                    repository.save(new Rating("It was an excellent course", 5, course));
                    repository.save(new Rating("It was ok, not great", 2, course));
                });

                courseRepository.findByCourseCode("DP001").ifPresent(course -> {
                    repository.save(new Rating("It was difficult to follow", 1, course));
                    repository.save(new Rating("The author explains very good", 4, course));
                });

                courseRepository.findByCourseCode("DG001").ifPresent(course -> {
                    repository.save(new Rating("Difficult concepts explained clearly", 5, course));
                    repository.save(new Rating("Extremely well organized", 5, course));
                    repository.save(new Rating("Very clear and well explained", 4, course));
                });

                log.info("Completed inserting Rating records !!!");
            }

            log.info("Ready to display all Rating records ...");

            repository.findAll().forEach(rating -> log.info(rating.toString()));
        };
    }
}
