package mbs_capsotme.mbs.controller;


import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrganizationController {
    
    private final DepartmentService departmentService;

    public OrganizationController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @RequestMapping(value = "/organization/showOrganizationChart")
    public String showOrganizationChart(Model model, HttpServletRequest req){

        List<Department> departments = departmentService.findDepartments();
        model.addAttribute("departments", departments);

        int div =  Integer.parseInt(req.getParameter("division"));
        if (div==1)
            return "divFunction/organizationChartDiv";
        else
            return "function/organizationChart";
    }

}
