package spring.servlet.web.springmvc.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.servlet.domain.member.Member;
import spring.servlet.domain.member.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value="/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() { //String으로 return해도 알아서 View로 판단
        return "new-form";
    }

    //@RequestMapping(value="/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {
        //파싱 지저분하게 안해도돼
//        String username = req.getParameter("username");
//        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

//        ModelAndView mv = new ModelAndView("save-result");
//        mv.addObject("member", member);
        //파라미터로 넘어온 모델에다가 그냥 담아주고 뷰는 String으로 리턴
        model.addAttribute("member", member);
        return "save-result";
    }

    //@RequestMapping(value="", method = RequestMethod.GET)
    @PostMapping("")
    public String memberList(Model model) {
        List<Member> members = memberRepository.findAll();
//        ModelAndView mv = new ModelAndView("members");
//        mv.addObject("members", members);
        model.addAttribute("members", members);
        return "members";
    }
}