package hello.login.domain.member;

import java.util.*;

public interface Repository {

    public Member save(Member member);

    public Member findById(Long id);

    public Optional<Member> findByLoginId(String loginId);

    public List<Member> findAll();

    public void clearStore();
}
