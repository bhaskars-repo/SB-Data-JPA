/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "course_tbl")
@Data
@NoArgsConstructor
// @RequiredArgsConstructor - Causes error
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "course_code", length = 5, nullable = false, unique = true)
    private String courseCode;

    @Column(name = "course_name", length = 50, nullable = false, unique = true)
    private String courseName;

    @Column(name = "level", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.LAZY)
    private Collection<Rating> ratings;

    @ManyToMany(mappedBy = "courses")
    private Collection<Member> members;

    public Course(String courseCode, String courseName, CourseLevel level) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.level = level;
    }

    @Override
    public String toString() {
        int count = ratings != null && ratings.size() > 0 ? ratings.size() : 0;
        int count2 = members != null && members.size() > 0 ? members.size() : 0;
        return String.format("Course(courseCode=%s, courseName=%s, ratingsCount=%d, membersCount=%s)",
                courseCode, courseName, count, count2);
    }
}
