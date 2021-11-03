package mbs_capsotme.mbs.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

}
