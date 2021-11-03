package mbs_capsotme.mbs.service;

import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void save(Department department) {
        departmentRepository.save(department);
    }

    public List<Department> findDepartments(){
        return departmentRepository.findAll();
    }

    public Optional<Department> findOne(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    public Optional<Department> findOne(String name) {
        return departmentRepository.findByName(name);
    }

    public void clearPersist() {
        departmentRepository.clearPersist();
    }

    public void deleteDepartment(Long id){
        departmentRepository.deleteDepartment(id);
    }
}
