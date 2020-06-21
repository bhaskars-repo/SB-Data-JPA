/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "rating_tbl")
@Data
@NoArgsConstructor
// @RequiredArgsConstructor - Causes Error
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "comments", length = 120)
    private String comments;

    @Column(name = "stars", columnDefinition = "SMALLINT CHECK (stars >= 1 AND stars <= 5)")
    private int stars;

    public Rating(String comments, int stars, Course course) {
        this.comments = comments;
        this.stars = stars;
        this.course = course;
    }

    @Override
    public String toString() {
        String code = course != null ? getCourse().getCourseCode() : "NA";
        return String.format("Rating(courseCode=%s, comments=%s, stars=%d)", code, comments, stars);
    }
}
