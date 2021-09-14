package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.Department;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DepartmentRepository {

    private final EntityManager em;

    public DepartmentRepository(EntityManager em) {
        this.em = em;
    }

    public Department save(Department department) {
        em.persist(department);
        return department;
    }
}
