package com.example.managerProject.entities;
import jakarta.persistence.*;

@Entity
@Table (name = "loan_applications")
public class LoanApplication {
    public LoanApplication() {
    }

    public LoanApplication(Client client_id, String status, Integer period, Integer sum) {
        this.client_id = client_id;
        this.status = status;
        this.period = period;
        this.sum = sum;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client_id;

    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Integer period;
    @Column(nullable = false)
    private Integer sum;


    public Long getId() {
        return id;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
