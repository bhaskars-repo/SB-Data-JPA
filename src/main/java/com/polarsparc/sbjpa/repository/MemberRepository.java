/*
    @Author: Bhaskar S
    @Blog:   https://www.polarsparc.com
    @Date:   20 Jun 2020
 */

package com.polarsparc.sbjpa.repository;

import com.polarsparc.sbjpa.model.Location;
import com.polarsparc.sbjpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmailId(String email);

    List<Member> findByLastName(String name);
    List<Member> findByLocation(Location loc);
}
