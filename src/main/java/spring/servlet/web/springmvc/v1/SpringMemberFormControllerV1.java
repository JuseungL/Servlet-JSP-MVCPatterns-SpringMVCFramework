package spring.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Component//컴포넌트 스캔을 통해서 스프링 빈으로 등록한다.
//@RequestMapping
public class SpringMemberFormControllerV1 {
    @RequestMapping("springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
