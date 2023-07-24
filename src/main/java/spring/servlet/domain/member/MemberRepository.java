package spring.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    HashMap은 동시성 문제가 고려되지 않기 때문에 ConcurrentHashMap, AtomicLong 사용 고려
    command shift T 로 자동으로 테스트 만들 수 있음
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();
    //싱글톤이기 때문에 private으로 막아야해
    public static MemberRepository getInstance() {
        return instance;
    }

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

    //테스트
    public void clearStore() {
        store.clear();
    }

}
