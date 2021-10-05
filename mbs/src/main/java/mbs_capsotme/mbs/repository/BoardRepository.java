package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.Board;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(em.find(Board.class, id));
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public void deleteBoard(Long id) {
        em.createQuery("delete from Board b where b.id=:bid").setParameter("bid",id).executeUpdate();
    }

    public List<Board> searchBoardByTitleOrContents(String search) {
        search = "%"+search+"%";
        return em.createQuery("select b from Board b where b.title like ?1 or b.contents like ?1")
                .setParameter(1,search).getResultList();
    }

    public List<Board> searchBoardByTitle(String search) {
        search = "%"+search+"%";
        return em.createQuery("select b from Board b where b.title like ?1")
                .setParameter(1,search).getResultList();
    }

    public List<Board> searchBoardByContents(String search) {
        search = "%"+search+"%";
        return em.createQuery("select b from Board b where b.contents like ?1")
                .setParameter(1,search).getResultList();
    }

    public void clearPersist() {
        em.flush();
        em.clear();
    }
}
