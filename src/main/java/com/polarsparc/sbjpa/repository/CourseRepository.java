/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.repository;

import com.polarsparc.sbjpa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course , Integer> {
    Optional<Course> findByCourseCode(String code);
}
