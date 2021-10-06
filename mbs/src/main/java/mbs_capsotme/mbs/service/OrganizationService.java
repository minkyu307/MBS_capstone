package mbs_capsotme.mbs.service;

import mbs_capsotme.mbs.repository.DepartmentRepository;
import mbs_capsotme.mbs.repository.MemberRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class OrganizationService {

    private final MemberRepository memberRepository;
    private final DepartmentRepository departmentRepository;

    public OrganizationService(MemberRepository memberRepository, DepartmentRepository departmentRepository) {
        this.memberRepository = memberRepository;
        this.departmentRepository = departmentRepository;
    }
}
