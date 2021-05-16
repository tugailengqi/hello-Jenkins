package hello.jenkins.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lengqi
 * @DATE: 2021/5/16
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String test(){
        return "hello-jenkins";
    }
}
