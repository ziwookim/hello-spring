package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository를 상속 받은 interface가 존재할 경우, 알아서 Spring Bean으로 구현체를 만들어준다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name= ?
    @Override
    Optional<Member> findByName(String name);
}
