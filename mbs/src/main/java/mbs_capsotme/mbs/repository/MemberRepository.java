package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class MemberRepository {

    private final EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Optional<Member> findByLoginId(String loginId){
        return Optional.ofNullable(em.createQuery(
                "select m from Member m where login_id = '"+loginId+"'", Member.class).getSingleResult());
    }

    /*public void addSessionIdById(String id, String sessionId) {
        em.createNativeQuery("insert into member(sessionId) values(?) where member.id=?")
                .setParameter(1, sessionId).setParameter(2, id).executeUpdate();
    }

    public void deleteSessionIdById(String id){
        em.createNativeQuery("insert into member(sessionId) values(null) where member.id=?")
                .setParameter(1, id).executeUpdate();
    }*/

    public List<Member> findMembersByDepartmentId(Long id){
        return em.createQuery("select m from Member m where m.department.id=?1").setParameter(1,id).getResultList();
    }

    public void deleteMember(Long id){
        em.createQuery("delete from Member m where m.id=?1").setParameter(1,id).executeUpdate();
    }

    public void clearPersist() {
        em.flush();
        em.clear();
    }
}
