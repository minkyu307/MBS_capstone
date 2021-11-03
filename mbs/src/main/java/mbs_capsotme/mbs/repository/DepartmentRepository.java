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

    public void save(Department department) {
        em.persist(department);
    }

    public Optional<Department> findById(Long id) {
        return Optional.ofNullable(em.find(Department.class, id));
    }

    public Optional<Department> findByName(String name) {
        Department department = em.createQuery(
                "select d from Department d where department_name='"+name+"'",Department.class).getSingleResult();
        return Optional.ofNullable(department);
    }

    public List<Department> findAll() {
        return em.createQuery("select d from Department d", Department.class).getResultList();
    }

    public void deleteDepartment(Long id){
        em.createQuery("delete from Department d where d.id=?1").setParameter(1,id).executeUpdate();
    }

    public void clearPersist() {
        em.flush();
        em.clear();
    }

}
