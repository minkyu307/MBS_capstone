package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.Department;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Optional<Department> findById(Long id) {
        return Optional.ofNullable(em.find(Department.class, id));
    }

    public List<Department> findAll() {
        return em.createQuery("select d from Department d", Department.class).getResultList();
    }
}
