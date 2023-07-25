package spring.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.servlet.web.frontcontroller.MyView;
import spring.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import spring.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import spring.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//"/front-controller/v2/"로 시작하는 모든 URL에 매핑. 즉
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    // 각 요청 URI에 해당하는 컨트롤러를 매핑하기 위한 맵
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        // 요청 URI에 따른 컨트롤러를 맵에 등록한다.
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");
        // 요청 URI를 변수에 담아둔다
        String requestURI = req.getRequestURI();

        // 요청 URI를 기준으로 해당하는 컨트롤러를 찾는다. 부모는 자식을 다 받을 수 있따.
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            // 해당하는 컨트롤러가 없으면 404 오류를 반환한다.
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(req,res);
        view.render(req,res);
    }
}
