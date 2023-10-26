package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장됨
    Optional<Member> findById(Long id); //저장소에서 id를 찾아옴
    // Optional : java8에 있는 기능으로, 만약 해당 값이 null이라면 반환할때 Optional로
    // null을 감싸서 반환하는 데에 사용됨
    Optional<Member> findByName(String name); //저장소에서 name을 찾아옴
    List<Member> findAll(); //저장된 모든 회원 리스트를 반환함
}
