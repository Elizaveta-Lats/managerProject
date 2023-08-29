package com.example.managerProject.services;

import java.util.List;

import com.example.managerProject.entities.LoanApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
@Transactional
public class ApplicationService {
    @PersistenceContext
    private EntityManager em;

    public List<LoanApplication> findAllApplications() {
        String queryText = "SELECT la FROM LoanApplication la";
        TypedQuery<LoanApplication> query = em.createQuery(queryText, LoanApplication.class);
        return query.getResultList();
    }

    public LoanApplication findApplicationByID(Long id) { return em.find(LoanApplication.class, id); }

    public List<LoanApplication> findApprovedApplications() {
        String queryText = "SELECT la FROM LoanApplication la where la.status = \"Одобрен\"";
        TypedQuery<LoanApplication> query = em.createQuery(queryText, LoanApplication.class);
        return query.getResultList();
    }

    public LoanApplication saveApplication(LoanApplication application) {
        em.persist(application);
        return application;
    }
}
