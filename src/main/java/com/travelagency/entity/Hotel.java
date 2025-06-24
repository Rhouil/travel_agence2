package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String adresse;
    
    @Column(nullable = false)
    private Integer etoiles;
    
    @ManyToOne
    @JoinColumn(name = "ville_id", nullable = false)
    private Ville ville;
    
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<Voyage> voyages;
    
    // Constructors
    public Hotel() {}
    
    public Hotel(String nom, String adresse, Integer etoiles, Ville ville) {
        this.nom = nom;
        this.adresse = adresse;
        this.etoiles = etoiles;
        this.ville = ville;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    
    public Integer getEtoiles() { return etoiles; }
    public void setEtoiles(Integer etoiles) { this.etoiles = etoiles; }
    
    public Ville getVille() { return ville; }
    public void setVille(Ville ville) { this.ville = ville; }
    
    public Set<Voyage> getVoyages() { return voyages; }
    public void setVoyages(Set<Voyage> voyages) { this.voyages = voyages; }
}
