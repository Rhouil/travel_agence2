package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "pays_id", nullable = false)
    private Pays pays;
    
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Departement> departements;
    
    // Constructors
    public Region() {}
    
    public Region(String nom, Pays pays) {
        this.nom = nom;
        this.pays = pays;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public Pays getPays() { return pays; }
    public void setPays(Pays pays) { this.pays = pays; }
    
    public Set<Departement> getDepartements() { return departements; }
    public void setDepartements(Set<Departement> departements) { this.departements = departements; }
}
