package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "autocars")
public class Autocar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String numeroImmatriculation;
    
    @Column(nullable = false)
    private Integer nombrePlaces;
    
    @ManyToOne
    @JoinColumn(name = "type_autocar_id", nullable = false)
    private TypeAutocar typeAutocar;
    
    @OneToMany(mappedBy = "autocar", cascade = CascadeType.ALL)
    private Set<Emplacement> emplacements;
    
    @ManyToMany(mappedBy = "autocars")
    private Set<Programmation> programmations;
    
    // Constructors
    public Autocar() {}
    
    public Autocar(String numeroImmatriculation, Integer nombrePlaces, TypeAutocar typeAutocar) {
        this.numeroImmatriculation = numeroImmatriculation;
        this.nombrePlaces = nombrePlaces;
        this.typeAutocar = typeAutocar;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNumeroImmatriculation() { return numeroImmatriculation; }
    public void setNumeroImmatriculation(String numeroImmatriculation) { this.numeroImmatriculation = numeroImmatriculation; }
    
    public Integer getNombrePlaces() { return nombrePlaces; }
    public void setNombrePlaces(Integer nombrePlaces) { this.nombrePlaces = nombrePlaces; }
    
    public TypeAutocar getTypeAutocar() { return typeAutocar; }
    public void setTypeAutocar(TypeAutocar typeAutocar) { this.typeAutocar = typeAutocar; }
    
    public Set<Emplacement> getEmplacements() { return emplacements; }
    public void setEmplacements(Set<Emplacement> emplacements) { this.emplacements = emplacements; }
    
    public Set<Programmation> getProgrammations() { return programmations; }
    public void setProgrammations(Set<Programmation> programmations) { this.programmations = programmations; }
}
