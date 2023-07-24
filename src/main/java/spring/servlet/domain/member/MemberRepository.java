package spring.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    HashMap은 동시성 문제가 고려되지 않기 때문에 ConcurrentHashMap, AtomicLong 사용 고려
    command shift T 로 자동으로 테스트 만들 수 있음
 */
/*
    HashMap은 동시성 문제가 고려되지 않기 때문에 ConcurrentHashMap, AtomicLong 사용 고려
    command shift T 로 자동으로 테스트 만들 수 있음
 */
public class MemberRepository {
    //static으로 MemberRespository 인스턴스 생성이 계속 되어도 1개만 생성
    //그리고 위와 같이 단순한 HashMap으로는 요청이 동시에 들어왔을 때 문제될 수 있다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤 인스턴스 - MemberRepository 클래스의 유일한 인스턴스를 담는 변수
    private static final MemberRepository instance = new MemberRepository();

    //싱글톤 인스턴스에 대한 액세스를 제공하는 정적 메서드
    //instance 변수에 저장된 MemberRepository 클래스의 고유한 단일 인스턴스를 반환
    public static MemberRepository getInstance() {
        return instance;
    }

    //생성자는 private로 선언되어 클래스 외부에서 클래스가 인스턴스화되는 것을 방지
    //이렇게 하면 싱글톤 인스턴스(instance)로 정의된 것 외에 다른 인스턴스를 만들 수 없다.
    private MemberRepository() {
    }

    public static Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //테스트 시 리셋
    public void clearStore() {
        store.clear();
    }
}
