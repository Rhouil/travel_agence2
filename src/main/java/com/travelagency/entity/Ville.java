package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "villes")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;
    
    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    private Set<PointDepart> pointsDepart;
    
    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    private Set<Hotel> hotels;
    
    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    private Set<User> clients;
    
    // Constructors
    public Ville() {}
    
    public Ville(String nom, Departement departement) {
        this.nom = nom;
        this.departement = departement;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }
    
    public Set<PointDepart> getPointsDepart() { return pointsDepart; }
    public void setPointsDepart(Set<PointDepart> pointsDepart) { this.pointsDepart = pointsDepart; }
    
    public Set<Hotel> getHotels() { return hotels; }
    public void setHotels(Set<Hotel> hotels) { this.hotels = hotels; }
    
    public Set<User> getClients() { return clients; }
    public void setClients(Set<User> clients) { this.clients = clients; }
}
