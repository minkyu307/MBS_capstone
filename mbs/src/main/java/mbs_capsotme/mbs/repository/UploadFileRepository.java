package mbs_capsotme.mbs.repository;

import mbs_capsotme.mbs.domain.UploadFiles;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UploadFileRepository {

    private final EntityManager em;

    public UploadFileRepository(EntityManager em) {
        this.em = em;
    }

    public UploadFiles save(UploadFiles file) {
        em.persist(file);
        return file;
    }

    public void deleteFile(String uuid){
        em.createQuery("delete from UploadFiles u where uuid=?1").setParameter(1,uuid).executeUpdate();
    }
}
