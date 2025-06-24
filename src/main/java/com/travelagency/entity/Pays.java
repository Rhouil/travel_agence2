package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pays")
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @OneToMany(mappedBy = "pays", cascade = CascadeType.ALL)
    private Set<Region> regions;
    
    // Constructors
    public Pays() {}
    
    public Pays(String nom) {
        this.nom = nom;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public Set<Region> getRegions() { return regions; }
    public void setRegions(Set<Region> regions) { this.regions = regions; }
}
