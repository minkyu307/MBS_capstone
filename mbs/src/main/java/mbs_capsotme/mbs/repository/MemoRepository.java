package mbs_capsotme.mbs.repository;

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
}
