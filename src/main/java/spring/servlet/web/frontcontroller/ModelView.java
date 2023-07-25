package spring.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter@Setter
public class ModelView {
    private String viewName; // view 논리 이름
    private Map<String, Object> model = new HashMap<>(); // model

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}