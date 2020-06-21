/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.model;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "member_tbl")
@Data
@NoArgsConstructor
// @RequiredArgsConstructor - Causes Error
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email_id", length = 25, nullable = false, unique = true)
    private String emailId;

    @Column(name = "first_name", length = 25, nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "last_name", length = 25, nullable = false)
    @NonNull
    private String lastName;

    @Column(name = "member_since", nullable = false)
    @NonNull
    private Date memberSince;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinTable(name = "members_courses",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Collection<Course> courses;

    public Member(String emailId, String firstName, String lastName, Date memberSince, Location location) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberSince = memberSince;
        this.location = location;
    }

    @Override
    public String toString() {
        int count = courses != null && courses.size() > 0 ? courses.size() : 0;
        return String.format("Member(emailId=%s, firstName=%s, lastName=%s, memberSince=%s, location=%s, coursesCount=%s)",
                emailId, firstName, lastName, memberSince, location, count);
    }
}
