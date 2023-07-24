package spring.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.servlet.domain.member.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();//싱글톤 - MemberRepository에서 생성자 private으로 막아놔서!

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter w = resp.getWriter();
        w.write("<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Member Form</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <form action=\"/servlet/members/save\" method=\"post\">\n" +
                        "      username: <input type=\"text\" name=\"username\" />\n" +
                        "      age:      <input type=\"text\" name=\"age\" />" +
                        "      <button type=\"submit\">전송</button>\n" +
                        "    </form>\n" +
                        "</body>\n" +
                    "</html>");
    }
}

