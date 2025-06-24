package com.travelagency.controller;

import com.travelagency.entity.*;
import com.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Set.of;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VoyageService voyageService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private VilleService villeService;
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private ProgrammationService programmationService;
    
    @Autowired
    private AutocarService autocarService;
    
    @Autowired
    private TypeAutocarService typeAutocarService;
    
    @Autowired
    private PointDepartService pointDepartService;
    
    @Autowired
    private PrixCalculatorService prixCalculatorService;
    
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("totalClients", userService.findByRoles(Role.CLIENT).size());
        model.addAttribute("totalVoyages", voyageService.findAll().size());
        model.addAttribute("totalReservations", reservationService.findAll().size());
        model.addAttribute("totalAutocars", autocarService.findAll().size());
        return "admin/dashboard";
    }
    
    // === GESTION DES UTILISATEURS ===
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }
    
    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("villes", villeService.findAll());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("roles", Role.values());
        return "admin/add-user";
    }
    
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user, @RequestParam String role) {
        Role userRole = Role.valueOf(role);
        user.setRoles(of(userRole));
        userService.save(user);
        return "redirect:/admin/users?success";
    }
    
    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("villes", villeService.findAll());
            model.addAttribute("genders", Gender.values());
            model.addAttribute("roles", Role.values());
            return "admin/edit-user";
        }
        return "redirect:/admin/users";
    }
    
    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user, 
                          @RequestParam String role,
                          @RequestParam(required = false) String newPassword) {
        User existingUser = userService.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPhone(user.getPhone());
            existingUser.setGender(user.getGender());
            existingUser.setVille(user.getVille());
            existingUser.setRoles(of(Role.valueOf(role)));
            
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                existingUser.setPassword(newPassword);
            }
            
            userService.save(existingUser);
        }
        return "redirect:/admin/users?updated";
    }
    
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users?deleted";
    }
    
    // === GESTION DES VOYAGES ===
    @GetMapping("/voyages")
    public String listVoyages(Model model, 
                             @RequestParam(required = false) String category,
                             @RequestParam(required = false) String destination) {
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
        return "admin/voyages";
    }
    
    @GetMapping("/voyages/add")
    public String addVoyageForm(Model model) {
        model.addAttribute("voyage", new Voyage());
        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("typeVoyages", TypeVoyage.values());
        model.addAttribute("typePensions", TypePension.values());
        model.addAttribute("pointsDepart", pointDepartService.findAll());
        return "admin/add-voyage";
    }
    
    @PostMapping("/voyages/add")
    public String addVoyage(@ModelAttribute Voyage voyage, @RequestParam List<Long> pointDepartIds) {
        Voyage savedVoyage = voyageService.save(voyage);
        
        // Associer les points de départ
        if (pointDepartIds != null) {
            Set<PointDepart> pointsDepart = pointDepartService.findByIds(pointDepartIds);
            savedVoyage.setPointsDepart(pointsDepart);
            voyageService.save(savedVoyage);
        }
        
        return "redirect:/admin/voyages?success";
    }
    
    @GetMapping("/voyages/edit/{id}")
    public String editVoyageForm(@PathVariable Long id, Model model) {
        Voyage voyage = voyageService.findById(id).orElse(null);
        if (voyage != null) {
            model.addAttribute("voyage", voyage);
            model.addAttribute("hotels", hotelService.findAll());
            model.addAttribute("typeVoyages", TypeVoyage.values());
            model.addAttribute("typePensions", TypePension.values());
            model.addAttribute("pointsDepart", pointDepartService.findAll());
            return "admin/edit-voyage";
        }
        return "redirect:/admin/voyages";
    }
    
    @PostMapping("/voyages/edit")
    public String editVoyage(@ModelAttribute Voyage voyage, @RequestParam List<Long> pointDepartIds) {
        Voyage existingVoyage = voyageService.findById(voyage.getId()).orElse(null);
        if (existingVoyage != null) {
            existingVoyage.setNom(voyage.getNom());
            existingVoyage.setDescription(voyage.getDescription());
            existingVoyage.setDuree(voyage.getDuree());
            existingVoyage.setTypeVoyage(voyage.getTypeVoyage());
            existingVoyage.setTypePension(voyage.getTypePension());
            existingVoyage.setHotel(voyage.getHotel());
            
            // Mettre à jour les points de départ
            if (pointDepartIds != null) {
                Set<PointDepart> pointsDepart = pointDepartService.findByIds(pointDepartIds);
                existingVoyage.setPointsDepart(pointsDepart);
            }
            
            voyageService.save(existingVoyage);
        }
        return "redirect:/admin/voyages?updated";
    }
    
    @GetMapping("/voyages/delete/{id}")
    public String deleteVoyage(@PathVariable Long id) {
        voyageService.deleteById(id);
        return "redirect:/admin/voyages?deleted";
    }
    
    // === GESTION DES PROGRAMMATIONS ===
    @GetMapping("/programmations")
    public String listProgrammations(Model model) {
        model.addAttribute("programmations", programmationService.findAll());
        model.addAttribute("voyages", voyageService.findAll());
        return "admin/programmations";
    }
    
    @GetMapping("/programmations/add")
    public String addProgrammationForm(Model model) {
        model.addAttribute("programmation", new Programmation());
        model.addAttribute("voyages", voyageService.findAll());
        model.addAttribute("autocars", autocarService.findAll());
        return "admin/add-programmation";
    }
    
    @PostMapping("/programmations/add")
    public String addProgrammation(@ModelAttribute Programmation programmation, 
                                  @RequestParam List<Long> autocarIds) {
        
        // Calcul automatique du prix avec le nouveau service
        BigDecimal prixCalcule = prixCalculatorService.calculerPrixProgrammation(programmation);
        programmation.setPrixBase(prixCalcule);
        
        Programmation savedProgrammation = programmationService.save(programmation);
        
        // Associer les autocars
        if (autocarIds != null) {
            Set<Autocar> autocars = autocarService.findByIds(autocarIds);
            savedProgrammation.setAutocars(autocars);
            programmationService.save(savedProgrammation);
        }
        
        return "redirect:/admin/programmations?success";
    }
    
    @GetMapping("/programmations/view/{id}")
    public String viewProgrammation(@PathVariable Long id, Model model) {
        Programmation programmation = programmationService.findById(id).orElse(null);
        if (programmation != null) {
            model.addAttribute("programmation", programmation);
            
            // Calcul des statistiques
            int totalPassagers = programmation.getReservations().stream()
                    .mapToInt(r -> r.getPassagers().size())
                    .sum();
            
            BigDecimal chiffreAffaires = programmation.getReservations().stream()
                    .map(Reservation::getPrixTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            int totalPlaces = programmation.getAutocars().stream()
                    .mapToInt(Autocar::getNombrePlaces)
                    .sum();
            
            int tauxOccupation = totalPlaces > 0 ? (totalPassagers * 100) / totalPlaces : 0;
            
            model.addAttribute("totalPassagers", totalPassagers);
            model.addAttribute("chiffreAffaires", chiffreAffaires);
            model.addAttribute("tauxOccupation", tauxOccupation);
            
            return "admin/view-programmation";
        }
        return "redirect:/admin/programmations";
    }
    
    // === GESTION DES AUTOCARS ===
    @GetMapping("/autocars")
    public String listAutocars(Model model) {
        model.addAttribute("autocars", autocarService.findAll());
        model.addAttribute("typesAutocar", typeAutocarService.findAll());
        return "admin/autocars";
    }
    
    @GetMapping("/autocars/add")
    public String addAutocarForm(Model model) {
        model.addAttribute("autocar", new Autocar());
        model.addAttribute("typesAutocar", typeAutocarService.findAll());
        return "admin/add-autocar";
    }
    
    @PostMapping("/autocars/add")
    public String addAutocar(@ModelAttribute Autocar autocar) {
        Autocar savedAutocar = autocarService.save(autocar);
        
        // Créer automatiquement les emplacements
        autocarService.creerEmplacements(savedAutocar);
        
        return "redirect:/admin/autocars?success";
    }
    
    @GetMapping("/autocars/view/{id}")
    public String viewAutocar(@PathVariable Long id, Model model) {
        Autocar autocar = autocarService.findById(id).orElse(null);
        if (autocar != null) {
            model.addAttribute("autocar", autocar);
            
            // Calculer les places occupées
            long placesOccupees = autocar.getEmplacements().stream()
                    .filter(e -> e.getPassager() != null)
                    .count();
            
            // Récupérer les numéros de sièges occupés
            Set<Integer> emplacementsOccupes = autocar.getEmplacements().stream()
                    .filter(e -> e.getPassager() != null)
                    .map(Emplacement::getNumeroSiege)
                    .collect(Collectors.toSet());
            
            model.addAttribute("placesOccupees", placesOccupees);
            model.addAttribute("emplacementsOccupes", emplacementsOccupes);
            
            return "admin/view-autocar";
        }
        return "redirect:/admin/autocars";
    }
    
    @GetMapping("/autocars/edit/{id}")
    public String editAutocarForm(@PathVariable Long id, Model model) {
        Autocar autocar = autocarService.findById(id).orElse(null);
        if (autocar != null) {
            model.addAttribute("autocar", autocar);
            model.addAttribute("typesAutocar", typeAutocarService.findAll());
            return "admin/edit-autocar";
        }
        return "redirect:/admin/autocars";
    }
    
    @PostMapping("/autocars/edit")
    public String editAutocar(@ModelAttribute Autocar autocar) {
        Autocar existingAutocar = autocarService.findById(autocar.getId()).orElse(null);
        if (existingAutocar != null) {
            existingAutocar.setNumeroImmatriculation(autocar.getNumeroImmatriculation());
            existingAutocar.setTypeAutocar(autocar.getTypeAutocar());
            // Note: Ne pas changer le nombre de places car cela affecterait les emplacements existants
            autocarService.save(existingAutocar);
        }
        return "redirect:/admin/autocars?updated";
    }
    
    @GetMapping("/autocars/delete/{id}")
    public String deleteAutocar(@PathVariable Long id) {
        autocarService.deleteById(id);
        return "redirect:/admin/autocars?deleted";
    }
    
    // === GESTION DES RÉSERVATIONS ===
    @GetMapping("/reservations")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "admin/reservations";
    }
    
    @GetMapping("/reservations/view/{id}")
    public String viewReservation(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.findById(id).orElse(null);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "admin/view-reservation";
        }
        return "redirect:/admin/reservations";
    }
    
    @GetMapping("/reservations/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return "redirect:/admin/reservations?deleted";
    }
    
    // === GESTION DES POINTS DE DÉPART ===
    @GetMapping("/points-depart")
    public String listPointsDepart(Model model) {
        model.addAttribute("pointsDepart", pointDepartService.findAll());
        model.addAttribute("villes", villeService.findAll());
        return "admin/points-depart";
    }
    
    @GetMapping("/points-depart/add")
    public String addPointDepartForm(Model model) {
        model.addAttribute("pointDepart", new PointDepart());
        model.addAttribute("villes", villeService.findAll());
        return "admin/add-point-depart";
    }
    
    @PostMapping("/points-depart/add")
    public String addPointDepart(@ModelAttribute PointDepart pointDepart) {
        pointDepartService.save(pointDepart);
        return "redirect:/admin/points-depart?success";
    }
    
    @GetMapping("/points-depart/edit/{id}")
    public String editPointDepartForm(@PathVariable Long id, Model model) {
        PointDepart pointDepart = pointDepartService.findById(id).orElse(null);
        if (pointDepart != null) {
            model.addAttribute("pointDepart", pointDepart);
            model.addAttribute("villes", villeService.findAll());
            return "admin/edit-point-depart";
        }
        return "redirect:/admin/points-depart";
    }
    
    @PostMapping("/points-depart/edit")
    public String editPointDepart(@ModelAttribute PointDepart pointDepart) {
        pointDepartService.save(pointDepart);
        return "redirect:/admin/points-depart?updated";
    }
    
    @GetMapping("/points-depart/delete/{id}")
    public String deletePointDepart(@PathVariable Long id) {
        pointDepartService.deleteById(id);
        return "redirect:/admin/points-depart?deleted";
    }
    
    // === GESTION DES HÔTELS ===
    @GetMapping("/hotels")
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("villes", villeService.findAll());
        return "admin/hotels";
    }
    
    @GetMapping("/hotels/add")
    public String addHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("villes", villeService.findAll());
        return "admin/add-hotel";
    }
    
    @PostMapping("/hotels/add")
    public String addHotel(@ModelAttribute Hotel hotel) {
        hotelService.save(hotel);
        return "redirect:/admin/hotels?success";
    }
    
    @GetMapping("/hotels/edit/{id}")
    public String editHotelForm(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.findById(id).orElse(null);
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
            model.addAttribute("villes", villeService.findAll());
            return "admin/edit-hotel";
        }
        return "redirect:/admin/hotels";
    }
    
    @PostMapping("/hotels/edit")
    public String editHotel(@ModelAttribute Hotel hotel) {
        hotelService.save(hotel);
        return "redirect:/admin/hotels?updated";
    }
    
    @GetMapping("/hotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteById(id);
        return "redirect:/admin/hotels?deleted";
    }

    // === CALCULATEUR DE PRIX ===
    @GetMapping("/calculateur-prix")
    public String calculateurPrix() {
        return "admin/calculateur-prix";
    }
}
