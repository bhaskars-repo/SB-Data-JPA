/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.component;

import com.polarsparc.sbjpa.model.Course;
import com.polarsparc.sbjpa.repository.CourseRepository;
import com.polarsparc.sbjpa.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class LinkMembersToCourses {

    private static final Logger log = LoggerFactory.getLogger(LinkMembersToCourses.class);

    private CourseRepository courseRepository;

    public LinkMembersToCourses(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Bean
    public ApplicationRunner membersToCourses(MemberRepository repository) {
        return args -> {
            log.info("Ready to setup Member to Course links ...");

            repository.findByEmailId("alice.blue@earth.co").ifPresent(member -> {
                Set<Course> courseList = new HashSet<>(3);
                courseRepository.findByCourseCode("AL001").ifPresent(course -> courseList.add(course));
                courseRepository.findByCourseCode("DG001").ifPresent(course -> courseList.add(course));
                member.setCourses(courseList);
                repository.save(member);
            });

            repository.findByEmailId("bob.b@space.in").ifPresent(member -> {
                Set<Course> courseList = new HashSet<>(3);
                courseRepository.findByCourseCode("DP001").ifPresent(course -> courseList.add(course));
                member.setCourses(courseList);
                repository.save(member);
            });

            repository.findByEmailId("c_green@mount.net").ifPresent(member -> {
                Set<Course> courseList = new HashSet<>(3);
                courseRepository.findByCourseCode("AZ001").ifPresent(course -> courseList.add(course));
                courseRepository.findByCourseCode("AZ002").ifPresent(course -> courseList.add(course));
                member.setCourses(courseList);
                repository.save(member);
            });

            log.info("Completed setup of Member to Course links !!!");
        };
    }
}
