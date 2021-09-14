package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.Memo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

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
}
