/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.repository;

import com.polarsparc.sbjpa.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
