package com.example.managerProject.entities;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "loan_agreements")
public class Agreement {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private LoanApplication application_id;
    private LocalDate signing_date;
    @Column(nullable = false)
    private String status;


    public Long getId() {
        return id;
    }

    public LoanApplication getApplication_id() {
        return application_id;
    }

    public void setApplication_id(LoanApplication application_id) {
        this.application_id = application_id;
    }

    public LocalDate getSigning_date() {
        return signing_date;
    }

    public void setSigning_date(LocalDate signing_date) {
        this.signing_date = signing_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
