package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    /**
        implementation 'org.springframework.boot:spring-boot-starter-data-jpa' 라이브러리가
        dbConnection 및 EntityManager까지 생성을 해줌.

        JPA != Spring Data JPA

        @Transactional 어노테이션 필수 관리
        JPA는 모든 데이터 변경이 다 @Transactional 어노테이션 안에서 이루어져야한다.
     */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
