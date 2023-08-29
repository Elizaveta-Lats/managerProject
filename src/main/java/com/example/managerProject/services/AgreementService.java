package com.example.managerProject.services;

import java.util.List;

import com.example.managerProject.entities.Agreement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
@Transactional
public class AgreementService {
    @PersistenceContext
    private EntityManager em;

    public List<Agreement> findAllAgreements() {
        TypedQuery<Agreement> query = em.createQuery("SELECT a FROM Agreement a", Agreement.class);
        return query.getResultList();
    }

    public Agreement findAgreementByID(Long id) { return em.find(Agreement.class, id); }

    public boolean haveAgreementByApplicationID(Long appId) {
        String sql = "SELECT a FROM Agreement a WHERE a.application_id = " + appId;
        TypedQuery<Agreement> query = em.createQuery(sql, Agreement.class);
        return !query.getResultList().isEmpty();
    }

    public Long findAgreementByApplicationID(Long appId) {
        String sql = "SELECT a FROM Agreement a WHERE a.application_id = " + appId;
        TypedQuery<Agreement> query = em.createQuery(sql, Agreement.class);
        if (query.getResultList().isEmpty()) return null;
        else return query.getResultList().get(0).getId();
    }

    public List<Agreement> findSignedAgreements() {
        String sql = "SELECT a FROM Agreement a WHERE a.status = \"Подписан\"";
        TypedQuery<Agreement> query = em.createQuery(sql, Agreement.class);
        return query.getResultList();
    }

    public Agreement saveAgreement(Agreement agreement) {
        em.persist(agreement);
        return agreement;
    }

    public Agreement updateAgreement(Agreement agreement) {
        em.merge(agreement);
        return agreement;
    }
}
