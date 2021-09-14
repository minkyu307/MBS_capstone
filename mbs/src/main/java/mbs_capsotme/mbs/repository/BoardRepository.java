package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.Board;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class BoardRepository {

    private final EntityManager em;

    public BoardRepository(EntityManager em) {
        this.em = em;
    }

    public Board save(Board board) {
        em.persist(board);
        return board;
    }
}
