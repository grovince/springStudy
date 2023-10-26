package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    // int 타입의 최대 범위 : 2147483647
    // 회원가입을 예시로 만약 2147483648 번째 회원 가입이 된다면 sequence를 int로 저장했을 때 오류가 발생함
    // -> int 타입의 최대 범위를 넘었기 때문에
    // Long 타입은 변수에 담기 전에 임시 메모리에 저장되는데 저장할 떄 만약
    // 최대 범위를 넘어도 값을 임시로 저장하기 위해서 0L(0 Long)을 사용한 것
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id는 시스템이 정하기 때문에 가입되는 회원마다 +1씩 해서 id를 정함
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 가능성이 있을 때 Optional 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //member가 반환됨 -> member를 리스트로 만들어서 반환함
    }

    public void clearStore(){ //컴파일할 때 파일의 순서는 신경쓰지 않고 돌리기 때문에(그때그때 바뀜)
        // 어떤 파일이 먼저 시작되느냐에 따라 결과가 매번 다르게 발생되는 것을 방지함
        // 만약 같은 이름의 객체를 만드는 클래스가 여러 개일때, 이 전에 먼저 실행된 코드의 결과(?)를 clear함
        store.clear();
    }
}


