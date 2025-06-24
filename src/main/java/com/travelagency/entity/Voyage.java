package com.travelagency.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "voyages")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Integer duree;
    
    @Enumerated(EnumType.STRING)
    private TypeVoyage typeVoyage;
    
    @Enumerated(EnumType.STRING)
    private TypePension typePension;
    
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
    
    @ManyToMany
    @JoinTable(
        name = "voyage_point_depart",
        joinColumns = @JoinColumn(name = "voyage_id"),
        inverseJoinColumns = @JoinColumn(name = "point_depart_id")
    )
    private Set<PointDepart> pointsDepart;
    
    @OneToMany(mappedBy = "voyage", cascade = CascadeType.ALL)
    private Set<Programmation> programmations;
    
    // Constructors
    public Voyage() {}
    
    public Voyage(String nom, String description, Integer duree, TypeVoyage typeVoyage, TypePension typePension, Hotel hotel) {
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.typeVoyage = typeVoyage;
        this.typePension = typePension;
        this.hotel = hotel;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getDuree() { return duree; }
    public void setDuree(Integer duree) { this.duree = duree; }
    
    public TypeVoyage getTypeVoyage() { return typeVoyage; }
    public void setTypeVoyage(TypeVoyage typeVoyage) { this.typeVoyage = typeVoyage; }
    
    public TypePension getTypePension() { return typePension; }
    public void setTypePension(TypePension typePension) { this.typePension = typePension; }
    
    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }
    
    public Set<PointDepart> getPointsDepart() { return pointsDepart; }
    public void setPointsDepart(Set<PointDepart> pointsDepart) { this.pointsDepart = pointsDepart; }
    
    public Set<Programmation> getProgrammations() { return programmations; }
    public void setProgrammations(Set<Programmation> programmations) { this.programmations = programmations; }
}

