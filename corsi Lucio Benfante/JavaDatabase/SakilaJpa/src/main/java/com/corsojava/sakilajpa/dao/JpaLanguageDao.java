package com.corsojava.sakilajpa.dao;

import com.corsojava.sakilajpa.model.Language;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author <a href="mailto:lucio.benfante@gmail.com">Lucio Benfante</a>
 */
@Repository
public class JpaLanguageDao implements LanguageDao {
    
    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Language> getAllLanguages() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Language l");
        return query.getResultList();
    }

    @Override
    public Language getLanguage(Short id) {
        return emf.createEntityManager().find(Language.class, id);
    }
    
}
