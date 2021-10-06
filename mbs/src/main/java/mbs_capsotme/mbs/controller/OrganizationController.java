package mbs_capsotme.mbs.controller;


import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrganizationController {
    
    private final DepartmentService departmentService;

    public OrganizationController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @RequestMapping(value = "/organization/showOrganizationChart")
    public String showOrganizationChart(Model model){

        List<Department> departments = departmentService.findDepartments();
        model.addAttribute("departments", departments);

        return "function/organizationChart";
    }

}
