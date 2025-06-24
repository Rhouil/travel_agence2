package com.travelagency.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "emplacements")
public class Emplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer numeroSiege;
    
    @ManyToOne
    @JoinColumn(name = "autocar_id", nullable = false)
    private Autocar autocar;
    
    @OneToOne(mappedBy = "emplacement")
    private Passager passager;
    
    // Constructors
    public Emplacement() {}
    
    public Emplacement(Integer numeroSiege, Autocar autocar) {
        this.numeroSiege = numeroSiege;
        this.autocar = autocar;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getNumeroSiege() { return numeroSiege; }
    public void setNumeroSiege(Integer numeroSiege) { this.numeroSiege = numeroSiege; }
    
    public Autocar getAutocar() { return autocar; }
    public void setAutocar(Autocar autocar) { this.autocar = autocar; }
    
    public Passager getPassager() { return passager; }
    public void setPassager(Passager passager) { this.passager = passager; }
}
