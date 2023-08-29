package com.example.managerProject.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name = "clients")
public class Client {
    public Client() {
    }

    public Client(String fio, String passport,
                  String family_status, String address, String phone_number,
                  String job_title, String job_organization, String job_period_start, String job_period_finish) {
        this.fio = fio;
        this.passport = passport;
        this.family_status = family_status;
        this.address = address;
        this.phone_number = phone_number;
        this.job_title = job_title;
        this.job_organization = job_organization;
        this.job_period_start = job_period_start;
        this.job_period_finish = job_period_finish;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fio;
    @Column(name = "passport", nullable = false)
    private String passport;
    private String family_status;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone_number;
    private String job_title;
    private String job_organization;
    private String job_period_start;
    private String job_period_finish;

    @OneToMany(mappedBy = "client_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanApplication> applications;

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFamily_status() {
        return family_status;
    }

    public void setFamily_status(String family_status) {
        this.family_status = family_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_organization() {
        return job_organization;
    }

    public void setJob_organization(String job_organization) {
        this.job_organization = job_organization;
    }


    public String getJob_period_start() {
        return job_period_start;
    }

    public void setJob_period_start(String job_period_start) {
        this.job_period_start = job_period_start;
    }

    public String getJob_period_finish() {
        return job_period_finish;
    }

    public void setJob_period_finish(String job_period_finish) {
        this.job_period_finish = job_period_finish;
    }

    public List<LoanApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<LoanApplication> applications) {
        this.applications = applications;
    }
}
