package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={},age={}", username, age);
        response.getWriter().write("ok hyhyhey");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,    //username이 리퀘스트 변수명이 됨
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok"; //Controller쓰면 이런경우는 뷰를찾게됨 or ResponseBody를 쓰면 http메시지로 콱 박아버려(restcontroller같은역할)
    }

    @ResponseBody //위에와 달리 생략가능
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,    //username 이 이름을 username으로 딱 고정시켜야해
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok"; //Controller쓰면 이런경우는 뷰를찾게됨 or ResponseBody를 쓰면 http메시지로 콱 박아버려(restcontroller같은역할)
    }

    //위에와 달리 생략가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,    //요청파라미터 이름과 같아야해
            int age) {
        log.info("username={}, age={}", username, age);
        return "ok4"; //Controller쓰면 이런경우는 뷰를찾게됨 or ResponseBody를 쓰면 http메시지로 콱 박아버려(restcontroller같은역할)
    }

    //위에와 달리 생략가능
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //이 값이 들어와야돼 안들어와도돼 required로 설정true가 default
            @RequestParam(required = false) Integer age) { //null이 들어갈수 있기때문에 int가아닌 Integer로 써야돼
//        int a = null;
//        integer b = null;
        log.info("username={}, age={}", username, age);
        return "ok"; //Controller쓰면 이런경우는 뷰를찾게됨 or ResponseBody를 쓰면 http메시지로 콱 박아버려(restcontroller같은역할)
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
//        int a = null;
//        integer b = null;
        log.info("username={}, age={}", username, age);
        return "ok"; //Controller쓰면 이런경우는 뷰를찾게됨 or ResponseBody를 쓰면 http메시지로 콱 박아버려(restcontroller같은역할)
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok"; //Controller쓰면 이런경우는 뷰를찾게됨 or ResponseBody를 쓰면 http메시지로 콱 박아버려(restcontroller같은역할)
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
        //HelloData 객체가 생성되고, 요청파라미터의 값도 모두 들어가 있어.
        log.info("helloData={}", helloData);
        log.info(helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData) {
        log.info("helloData={}", helloData);

        return "ok";
    }
}
