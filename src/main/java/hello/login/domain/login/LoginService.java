package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId,String password){
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get();
//        if(member.getPassword().equals(password)){
//            return member;
//        }else {
//            return null;
//        }
//  위의 코드는 아래와 같음

        return memberRepository.findByLoginId(loginId)
                .filter(m->m.getPassword().equals(password))
                .orElse(null);
    }
}
