package com.travelagency.service;

import com.travelagency.entity.Programmation;
import com.travelagency.entity.TypeVoyage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PrixCalculatorService {
    
    private static final BigDecimal PRIX_BASE_JOUR = new BigDecimal("50.00");
    
    public BigDecimal calculerPrixProgrammation(Programmation programmation) {
        BigDecimal prix = PRIX_BASE_JOUR;
        
        // 1. Calcul selon le type de voyage
        prix = appliquerCoefficientTypeVoyage(prix, programmation.getVoyage().getTypeVoyage());
        
        // 2. Calcul selon la durée
        prix = prix.multiply(new BigDecimal(programmation.getVoyage().getDuree()));
        
        // 3. Calcul selon la période (saisonnalité)
        prix = appliquerCoefficientSaisonnier(prix, programmation.getDateDepart());
        
        // 4. Calcul selon la destination (région)
        prix = appliquerCoefficientDestination(prix, programmation.getVoyage().getHotel().getVille().getDepartement().getRegion().getNom());
        
        // 5. Calcul selon le standing de l'hôtel
        prix = appliquerCoefficientHotel(prix, programmation.getVoyage().getHotel().getEtoiles());
        
        return prix.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    private BigDecimal appliquerCoefficientTypeVoyage(BigDecimal prix, TypeVoyage typeVoyage) {
        switch (typeVoyage) {
            case SEJOUR:
                return prix.multiply(new BigDecimal("1.0")); // Prix normal
            case CIRCUIT:
                return prix.multiply(new BigDecimal("1.5")); // +50% pour les circuits
            case EXCURSION:
                return prix.multiply(new BigDecimal("0.7")); // -30% pour les excursions
            default:
                return prix;
        }
    }
    
    private BigDecimal appliquerCoefficientSaisonnier(BigDecimal prix, LocalDate dateDepart) {
        int mois = dateDepart.getMonthValue();
        
        if (mois >= 6 && mois <= 8) {
            // Haute saison été (juin-août)
            return prix.multiply(new BigDecimal("1.4")); // +40%
        } else if (mois == 12 || mois <= 2) {
            // Saison hiver (décembre-février)
            return prix.multiply(new BigDecimal("1.2")); // +20%
        } else if (mois >= 3 && mois <= 5) {
            // Printemps (mars-mai)
            return prix.multiply(new BigDecimal("1.1")); // +10%
        } else {
            // Automne (septembre-novembre)
            return prix.multiply(new BigDecimal("0.9")); // -10%
        }
    }
    
    private BigDecimal appliquerCoefficientDestination(BigDecimal prix, String region) {
        switch (region.toLowerCase()) {
            case "île-de-france":
                return prix.multiply(new BigDecimal("1.3")); // +30% pour Paris
            case "provence-alpes-côte d'azur":
                return prix.multiply(new BigDecimal("1.2")); // +20% pour la Côte d'Azur
            case "andalousie":
                return prix.multiply(new BigDecimal("1.1")); // +10% pour l'Andalousie
            case "toscane":
                return prix.multiply(new BigDecimal("1.15")); // +15% pour la Toscane
            default:
                return prix; // Prix normal pour les autres régions
        }
    }
    
    private BigDecimal appliquerCoefficientHotel(BigDecimal prix, Integer etoiles) {
        switch (etoiles) {
            case 1:
                return prix.multiply(new BigDecimal("0.7")); // -30%
            case 2:
                return prix.multiply(new BigDecimal("0.8")); // -20%
            case 3:
                return prix.multiply(new BigDecimal("1.0")); // Prix normal
            case 4:
                return prix.multiply(new BigDecimal("1.3")); // +30%
            case 5:
                return prix.multiply(new BigDecimal("1.6")); // +60%
            default:
                return prix;
        }
    }
    
    public String getExplicationCalcul(Programmation programmation) {
        StringBuilder explication = new StringBuilder();
        explication.append("Calcul du prix pour ").append(programmation.getVoyage().getNom()).append(":\n");
        explication.append("- Prix de base par jour: ").append(PRIX_BASE_JOUR).append("€\n");
        explication.append("- Type de voyage (").append(programmation.getVoyage().getTypeVoyage()).append("): ");
        
        switch (programmation.getVoyage().getTypeVoyage()) {
            case SEJOUR:
                explication.append("coefficient 1.0\n");
                break;
            case CIRCUIT:
                explication.append("coefficient 1.5 (+50%)\n");
                break;
            case EXCURSION:
                explication.append("coefficient 0.7 (-30%)\n");
                break;
        }
        
        explication.append("- Durée: ").append(programmation.getVoyage().getDuree()).append(" jours\n");
        explication.append("- Période: ").append(getSaisonDescription(programmation.getDateDepart())).append("\n");
        explication.append("- Destination: ").append(programmation.getVoyage().getHotel().getVille().getDepartement().getRegion().getNom()).append("\n");
        explication.append("- Hôtel ").append(programmation.getVoyage().getHotel().getEtoiles()).append(" étoiles\n");
        explication.append("= Prix final: ").append(calculerPrixProgrammation(programmation)).append("€");
        
        return explication.toString();
    }
    
    private String getSaisonDescription(LocalDate date) {
        int mois = date.getMonthValue();
        if (mois >= 6 && mois <= 8) {
            return "Haute saison été (+40%)";
        } else if (mois == 12 || mois <= 2) {
            return "Saison hiver (+20%)";
        } else if (mois >= 3 && mois <= 5) {
            return "Printemps (+10%)";
        } else {
            return "Automne (-10%)";
        }
    }
}
