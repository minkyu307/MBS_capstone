package mbs_capsotme.mbs.controller;


import lombok.RequiredArgsConstructor;
import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.service.DepartmentService;
import mbs_capsotme.mbs.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrganizationController {
    
    private final DepartmentService departmentService;
    private final MemberService memberService;


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

    @RequestMapping(value = "/organization/delete")
    public String deleteDepartment(HttpServletRequest req){

        Department department = departmentService.findOne(Long.parseLong(req.getParameter("id"))).get();
        try {
            List<Member> dpmtMembers = memberService.findMembersByDepartmentId(department.getId());
            for (Member dpmtMember : dpmtMembers) {
                dpmtMember.setPosition(null);
                dpmtMember.setDepartment(null);
                memberService.joinAndSave(dpmtMember);
            }
        }catch (Exception e){

        }
        departmentService.deleteDepartment(department.getId());


        return "redirect:/admin";
    }
}
