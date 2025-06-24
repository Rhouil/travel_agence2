package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departements")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private Set<Ville> villes;
    
    // Constructors
    public Departement() {}
    
    public Departement(String nom, Region region) {
        this.nom = nom;
        this.region = region;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public Region getRegion() { return region; }
    public void setRegion(Region region) { this.region = region; }
    
    public Set<Ville> getVilles() { return villes; }
    public void setVilles(Set<Ville> villes) { this.villes = villes; }
}
