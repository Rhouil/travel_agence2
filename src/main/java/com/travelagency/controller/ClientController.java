package com.travelagency.controller;

import com.travelagency.entity.*;
import com.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/client")
@PreAuthorize("hasRole('CLIENT')")
public class ClientController {
    
    @Autowired
    private VoyageService voyageService;
    
    @Autowired
    private ProgrammationService programmationService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmplacementService emplacementService;
    
    @Autowired
    private PointDepartService pointDepartService;
    
    @GetMapping("/dashboard")
    public String clientDashboard(Authentication authentication, Model model) {
        User client = userService.findByUsername(authentication.getName()).orElse(null);
        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("reservations", client.getReservations());
        }
        return "client/dashboard";
    }
    
    @GetMapping("/voyages")
    public String listVoyages(Model model, 
                             @RequestParam(required = false) String category,
                             @RequestParam(required = false) String destination,
                             @RequestParam(required = false) String dateDepart) {
        
        List<Voyage> voyages;
        
        if (category != null && !category.isEmpty()) {
            voyages = voyageService.findByTypeVoyage(TypeVoyage.valueOf(category));
        } else if (destination != null && !destination.isEmpty()) {
            voyages = voyageService.findByHotelVilleNomContaining(destination);
        } else {
            voyages = voyageService.findAll();
        }
        
        model.addAttribute("voyages", voyages);
        model.addAttribute("typeVoyages", TypeVoyage.values());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedDestination", destination);
        return "client/voyages";
    }
    
    @GetMapping("/voyages/{id}")
    public String voyageDetails(@PathVariable Long id, Model model) {
        Voyage voyage = voyageService.findById(id).orElse(null);
        if (voyage != null) {
            model.addAttribute("voyage", voyage);
            model.addAttribute("programmations", voyage.getProgrammations());
            model.addAttribute("pointsDepart", voyage.getPointsDepart());
        }
        return "client/voyage-details";
    }
    
    @GetMapping("/reservation/{programmationId}")
    public String reservationForm(@PathVariable Long programmationId, Model model) {
        Programmation programmation = programmationService.findById(programmationId).orElse(null);
        if (programmation != null) {
            model.addAttribute("programmation", programmation);
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("pointsDepart", programmation.getVoyage().getPointsDepart());
            // Récupérer les emplacements disponibles
            model.addAttribute("emplacementsDisponibles", 
                emplacementService.findEmplacementsDisponibles(programmation.getAutocars()));
        }
        return "client/reservation-form";
    }
    
    @PostMapping("/reservation")
    public String makeReservation(@ModelAttribute Reservation reservation,
                                 @RequestParam Long programmationId,
                                 @RequestParam(required = false) Long emplacementId,
                                 @RequestParam(required = false) Long pointDepartId,
                                 @RequestParam(required = false) List<String> passagerNoms,
                                 @RequestParam(required = false) List<String> passagerPrenoms,
                                 @RequestParam(required = false) List<String> passagerTelephones,
                                 @RequestParam(required = false) List<String> passagerEmails,
                                 Authentication authentication) {
        
        User client = userService.findByUsername(authentication.getName()).orElse(null);
        Programmation programmation = programmationService.findById(programmationId).orElse(null);
        
        if (client != null && programmation != null) {
            reservation.setClient(client);
            reservation.setProgrammation(programmation);
            reservation.setDateReservation(LocalDateTime.now());
            
            // Calcul du prix
            BigDecimal prixBase = programmation.getPrixBase();
            int nombrePassagers = passagerNoms != null ? passagerNoms.size() : 1;
            prixBase = prixBase.multiply(new BigDecimal(nombrePassagers));
            
            if (reservation.getAssuranceAnnulation()) {
                prixBase = prixBase.multiply(new BigDecimal("1.1"));
            }
            if (reservation.getChambreSupplementaire()) {
                prixBase = prixBase.add(new BigDecimal("100"));
            }
            reservation.setPrixTotal(prixBase);
            
            // Sauvegarder la réservation
            Reservation savedReservation = reservationService.save(reservation);
            
            // Ajouter les passagers
            if (passagerNoms != null) {
                for (int i = 0; i < passagerNoms.size(); i++) {
                    Passager passager = new Passager();
                    passager.setNom(passagerNoms.get(i));
                    passager.setPrenom(passagerPrenoms.get(i));
                    passager.setTelephone(passagerTelephones.get(i));
                    passager.setEmail(passagerEmails.get(i));
                    passager.setReservation(savedReservation);
                    
                    // Assigner un emplacement si disponible
                    if (emplacementId != null && i == 0) {
                        Emplacement emplacement = emplacementService.findById(emplacementId).orElse(null);
                        if (emplacement != null) {
                            passager.setEmplacement(emplacement);
                        }
                    }
                    
                    reservationService.savePassager(passager);
                }
            }
            
            return "redirect:/client/dashboard?success";
        }
        
        return "redirect:/client/voyages?error";
    }
    
    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        User client = userService.findByUsername(authentication.getName()).orElse(null);
        if (client != null) {
            model.addAttribute("client", client);
        }
        return "client/profile";
    }
    
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user, Authentication authentication) {
        User existingClient = userService.findByUsername(authentication.getName()).orElse(null);
        if (existingClient != null) {
            existingClient.setFirstName(user.getFirstName());
            existingClient.setLastName(user.getLastName());
            existingClient.setEmail(user.getEmail());
            existingClient.setPhone(user.getPhone());
            existingClient.setGender(user.getGender());
            existingClient.setVille(user.getVille());
            userService.save(existingClient);
            return "redirect:/client/profile?success";
        }
        return "redirect:/client/profile?error";
    }
}
