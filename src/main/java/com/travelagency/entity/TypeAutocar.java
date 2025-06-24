package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types_autocar")
public class TypeAutocar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "typeAutocar", cascade = CascadeType.ALL)
    private Set<Autocar> autocars;
    
    // Constructors
    public TypeAutocar() {}
    
    public TypeAutocar(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Set<Autocar> getAutocars() { return autocars; }
    public void setAutocars(Set<Autocar> autocars) { this.autocars = autocars; }
}
