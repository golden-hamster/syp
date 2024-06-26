package com.isack.syp.member.repository;

import com.isack.syp.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByUsername(String username);

    Optional<Member> findByUsernameAndPassword(String username, String password);
}
