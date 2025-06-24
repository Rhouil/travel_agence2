package com.travelagency.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "programmations")
public class Programmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate dateDepart;
    
    @Column(nullable = false)
    private LocalDate dateRetour;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixBase;
    
    @ManyToOne
    @JoinColumn(name = "voyage_id", nullable = false)
    private Voyage voyage;
    
    @ManyToMany
    @JoinTable(
        name = "programmation_autocar",
        joinColumns = @JoinColumn(name = "programmation_id"),
        inverseJoinColumns = @JoinColumn(name = "autocar_id")
    )
    private Set<Autocar> autocars;
    
    @OneToMany(mappedBy = "programmation", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
    
    // Constructors
    public Programmation() {}
    
    public Programmation(LocalDate dateDepart, LocalDate dateRetour, BigDecimal prixBase, Voyage voyage) {
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.prixBase = prixBase;
        this.voyage = voyage;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDate getDateDepart() { return dateDepart; }
    public void setDateDepart(LocalDate dateDepart) { this.dateDepart = dateDepart; }
    
    public LocalDate getDateRetour() { return dateRetour; }
    public void setDateRetour(LocalDate dateRetour) { this.dateRetour = dateRetour; }
    
    public BigDecimal getPrixBase() { return prixBase; }
    public void setPrixBase(BigDecimal prixBase) { this.prixBase = prixBase; }
    
    public Voyage getVoyage() { return voyage; }
    public void setVoyage(Voyage voyage) { this.voyage = voyage; }
    
    public Set<Autocar> getAutocars() { return autocars; }
    public void setAutocars(Set<Autocar> autocars) { this.autocars = autocars; }
    
    public Set<Reservation> getReservations() { return reservations; }
    public void setReservations(Set<Reservation> reservations) { this.reservations = reservations; }
}
