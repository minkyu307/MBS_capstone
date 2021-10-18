package mbs_capsotme.mbs.controller;

import com.sun.jdi.event.StepEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DivisionController {

    @RequestMapping(value = "/division/notDivision")
    public String notDiv() {
        return "notDiv";
    }

    @RequestMapping(value = "/division/2Division")
    public String twoDiv() {
        return "2Div";
    }

    @RequestMapping(value = "/division/4Division")
    public String fourDiv() {
        return "4Div";
    }

    @RequestMapping(value = "/division/divSelect")
    public String goDivSelect(){
        return "divSelectFunction";
    }
}
