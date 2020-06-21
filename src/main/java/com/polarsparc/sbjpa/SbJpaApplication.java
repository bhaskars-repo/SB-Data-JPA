/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa;

import com.polarsparc.sbjpa.component.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbJpaApplication {

    public static void main(String[] args) {
        /*
            This is to force the order in which the different tables are initialized with database records.
            The @DependsOn annotation in the different xxxLoaders did *NOT* work
         */
        SpringApplication.run(new Class[] {SbJpaApplication.class,
                CoursesLoader.class,
                LocationsLoader.class,
                MembersLoader.class,
                RatingsLoader.class,
                LinkMembersToCourses.class}, args);
    }

}
