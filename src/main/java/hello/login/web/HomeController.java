package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.Repository;
import hello.login.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final Repository memberRepository;


//    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false)Member loginMember, Model model){

        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model){

        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }

}