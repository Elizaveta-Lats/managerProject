package com.example.managerProject.services;

import java.util.List;

import com.example.managerProject.entities.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
@Transactional
public class ClientService {
    @PersistenceContext
    private EntityManager em;

    public List<Client> findAllClients() {
        TypedQuery<Client> query = em.createQuery("SELECT client FROM Client client", Client.class);
        return query.getResultList();
    }

    public List<Client> findClientsByFIO(String fio) {
        String sql = "SELECT client FROM Client client WHERE LOWER(client.fio) LIKE LOWER(\"%" + fio + "%\")";
        TypedQuery<Client> query = em.createQuery(sql, Client.class);
        return query.getResultList();
    }
    public Client findClientByPassport(String passport) {
        String sql = "SELECT client FROM Client client WHERE client.passport LIKE \"" + passport + "\"";
        TypedQuery<Client> query = em.createQuery(sql, Client.class);
        if (query.getResultList().isEmpty()) return null;
        else return query.getResultList().get(0);
    }

    public List<Client> findClientByPassportAsList(String passport) {
        String sql = "SELECT client FROM Client client WHERE client.passport LIKE \"" + passport + "\"";
        TypedQuery<Client> query = em.createQuery(sql, Client.class);
        return query.getResultList();
    }

    public List<Client> findClientsByPhone(String phone) {
        String sql = "SELECT client FROM Client client WHERE client.phone_number LIKE \"" + phone + "\"";
        TypedQuery<Client> query = em.createQuery(sql, Client.class);
        return query.getResultList();
    }

    public Client saveClient(Client client) {
        em.persist(client);
        return client;
    }

}
