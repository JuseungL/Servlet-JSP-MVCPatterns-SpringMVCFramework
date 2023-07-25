package spring.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.servlet.domain.member.Member;
import spring.servlet.domain.member.MemberRepository;

import java.io.IOException;
@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 클라이언트로부터 받은 사용자 이름과 나이 정보를 가져온다.
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        // 사용자 이름과 나이로 Member 객체를 생성한다.
        Member member = new Member(username, age);
        // Member 객체를 MemberRepository에 저장한다.
        memberRepository.save(member);

        // Model에 데이터를 보관한다.
        req.setAttribute("member", member);

        // 뷰 페이지 경로를 지정한다.
        String viewPath = "/WEB-INF/views/save-result.jsp";
        // Dispatcher를 이용하여 뷰 페이지로 포워딩한다.
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, res);
    }
}
