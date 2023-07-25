package spring.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * MVC패턴
 * 컨트롤러
 */
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //렌더링하는 데 사용될 JSP 뷰의 경로를 viewPath에 담음
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //Dispatcher: 요청 및 응답 객체를 다른 리소스(이 경우 JSP 뷰)로 전달할 수 있게 해주는 객체
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //forward() : 다른 서블릿이나 JSP로 이동. 서버 내부에서 다시 호출이 발생
        dispatcher.forward(request, response);
    }
}