package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class MemoRepository {

    private final EntityManager em;

    public MemoRepository(EntityManager em) {
        this.em = em;
    }

    public Memo save(Memo memo) {
        em.persist(memo);
        return memo;
    }

    public Optional<Memo> findById(Long id) {
        return Optional.ofNullable(em.find(Memo.class, id));
    }

    public List<Memo> findALl() {
        return em.createQuery("select m from Memo m", Memo.class).getResultList();
    }

    public List<Memo> findAllByMember(Member member){
        return em.createQuery("select m from Memo m inner join m.member b where b=: member1")
                .setParameter("member1",member).getResultList();
    }

    public void deleteMemo(Long id){
        em.createQuery("delete from Memo m where m.id=:mid").setParameter("mid",id).executeUpdate();
    }

    public void deleteMemoByWriterId(Long id){
        em.createQuery("delete from Memo m where m.member.id=?1").setParameter(1,id).executeUpdate();
    }

    public void clearPersist() {
        em.flush();
        em.clear();
    }
}
