package spring.servlet.web.frontcontroller.v5;

import com.sun.jdi.ObjectReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import spring.servlet.web.frontcontroller.ModelView;
import spring.servlet.web.frontcontroller.MyView;
import spring.servlet.web.frontcontroller.v3.ControllerV3;
import spring.servlet.web.frontcontroller.v3.FrontControllerServletV3;
import spring.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import spring.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import spring.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import spring.servlet.web.frontcontroller.v4.ControllerV4;
import spring.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import spring.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import spring.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import spring.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import spring.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //Object를 넣은 이유는 뭐든 다 넣으려고
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //v4추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        //handler를 찾음 - MemberFormControllerV3 반환
        Object handler = getHandler(req);

        if (handler == null) {
            // 해당하는 핸들러가 없으면 404 오류를 반환한다.
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //HandlerAdapter찾음 - ControllerV3HandlerAdapter반환
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //handle호출 - MemberFormControllerV3를 Controller로 쓰겠다. -> 그 결과 모델 뷰 반환
        ModelView mv = adapter.handle(req, res, handler);

        String viewName = mv.getViewName();//논리이름 new-form
        MyView view = viewResolver(viewName); //뷰 리졸버 부름

        view.render(mv.getModel(), req, res);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        //handlerAdapter.iter하고 엔터하면 자동 for문
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler="+handler);
    }

    private Object getHandler(HttpServletRequest req) {
        // 요청 URI를 변수에 담아둔다
        String requestURI = req.getRequestURI();
        // 요청 URI를 기준으로 해당하는 컨트롤러를 찾는다. 부모는 자식을 다 받을 수 있다.
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
