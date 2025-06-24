package com.travelagency.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime dateReservation;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixTotal;
    
    @Column(nullable = false)
    private Boolean assuranceAnnulation = false;
    
    @Column(nullable = false)
    private Boolean chambreSupplementaire = false;
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;
    
    @ManyToOne
    @JoinColumn(name = "programmation_id", nullable = false)
    private Programmation programmation;
    
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<Passager> passagers;
    
    // Constructors
    public Reservation() {}
    
    public Reservation(LocalDateTime dateReservation, BigDecimal prixTotal, User client, Programmation programmation) {
        this.dateReservation = dateReservation;
        this.prixTotal = prixTotal;
        this.client = client;
        this.programmation = programmation;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }
    
    public BigDecimal getPrixTotal() { return prixTotal; }
    public void setPrixTotal(BigDecimal prixTotal) { this.prixTotal = prixTotal; }
    
    public Boolean getAssuranceAnnulation() { return assuranceAnnulation; }
    public void setAssuranceAnnulation(Boolean assuranceAnnulation) { this.assuranceAnnulation = assuranceAnnulation; }
    
    public Boolean getChambreSupplementaire() { return chambreSupplementaire; }
    public void setChambreSupplementaire(Boolean chambreSupplementaire) { this.chambreSupplementaire = chambreSupplementaire; }
    
    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }
    
    public Programmation getProgrammation() { return programmation; }
    public void setProgrammation(Programmation programmation) { this.programmation = programmation; }
    
    public Set<Passager> getPassagers() { return passagers; }
    public void setPassagers(Set<Passager> passagers) { this.passagers = passagers; }
}
