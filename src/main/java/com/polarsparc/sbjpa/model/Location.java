/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "location_tbl", uniqueConstraints = {
        @UniqueConstraint(name = "city_state_uniq", columnNames = {"city", "state"}),
        @UniqueConstraint(name = "zip_uniq", columnNames = {"zip"})
})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "city", length = 25, nullable = false)
    @NonNull
    private String city;

    @Column(name = "state", length = 25, nullable = false)
    @NonNull
    private String state;

    @Column(name = "zip", length = 5, nullable = false, unique = true)
    @NonNull
    private String zip;
}
