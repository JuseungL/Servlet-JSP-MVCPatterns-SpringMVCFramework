package spring.servlet.web.frontcontroller.v4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.servlet.web.frontcontroller.ModelView;
import spring.servlet.web.frontcontroller.MyView;
import spring.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import spring.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import spring.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//"/front-controller/v3/"로 시작하는 모든 URL에 매핑. 즉
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    // 각 요청 URI에 해당하는 컨트롤러를 매핑하기 위한 맵
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        // 요청 URI에 따른 컨트롤러를 맵에 등록한다.
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");
        // 요청 URI를 변수에 담아둔다
        String requestURI = req.getRequestURI();

        // 요청 URI를 기준으로 해당하는 컨트롤러를 찾는다. 부모는 자식을 다 받을 수 있다.
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            // 해당하는 컨트롤러가 없으면 404 오류를 반환한다.
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        //view.render(mv.getModel(), req, res); 직접 모델 꺼낼 필요 없어
        view.render(model, req, res);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();

        //paramMap이라는 HashMap을 만들어서 getParametersNames()로 모든 파라미터 명을 다 불러오고
        //해당 키 이름을 돌리면서 getParameter(paramName)으로 value값도 넣어준다.
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
