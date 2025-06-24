-- Insertion des données de base pour l'agence de voyage

-- Pays
INSERT INTO pays (nom) VALUES ('France');
INSERT INTO pays (nom) VALUES ('Espagne');
INSERT INTO pays (nom) VALUES ('Italie');

-- Régions
INSERT INTO regions (nom, pays_id) VALUES ('Île-de-France', 1);
INSERT INTO regions (nom, pays_id) VALUES ('Provence-Alpes-Côte d''Azur', 1);
INSERT INTO regions (nom, pays_id) VALUES ('Andalousie', 2);
INSERT INTO regions (nom, pays_id) VALUES ('Toscane', 3);

-- Départements
INSERT INTO departements (nom, region_id) VALUES ('Paris', 1);
INSERT INTO departements (nom, region_id) VALUES ('Var', 2);
INSERT INTO departements (nom, region_id) VALUES ('Séville', 3);
INSERT INTO departements (nom, region_id) VALUES ('Florence', 4);

-- Villes
INSERT INTO villes (nom, departement_id) VALUES ('Paris', 1);
INSERT INTO villes (nom, departement_id) VALUES ('Saint-Tropez', 2);
INSERT INTO villes (nom, departement_id) VALUES ('Séville', 3);
INSERT INTO villes (nom, departement_id) VALUES ('Florence', 4);

-- Points de départ
INSERT INTO points_depart (nom, adresse, ville_id) VALUES ('Gare de Lyon', '20 Boulevard Diderot, 75012 Paris', 1);
INSERT INTO points_depart (nom, adresse, ville_id) VALUES ('Place de la République', 'Place de la République, 75003 Paris', 1);

-- Hôtels
INSERT INTO hotels (nom, adresse, etoiles, ville_id) VALUES ('Hôtel Le Meurice', '228 Rue de Rivoli, 75001 Paris', 5, 1);
INSERT INTO hotels (nom, adresse, etoiles, ville_id) VALUES ('Villa Belrose', 'Boulevard des Crêtes, 83580 Gassin', 5, 2);
INSERT INTO hotels (nom, adresse, etoiles, ville_id) VALUES ('Hotel Alfonso XIII', 'San Fernando 2, 41004 Sevilla', 5, 3);
INSERT INTO hotels (nom, adresse, etoiles, ville_id) VALUES ('Hotel Davanzati', 'Via Porta Rossa 5, 50123 Firenze', 4, 4);

-- Types d'autocar
INSERT INTO types_autocar (nom, description) VALUES ('Standard', 'Autocar standard avec sièges confortables');
INSERT INTO types_autocar (nom, description) VALUES ('Premium', 'Autocar premium avec sièges inclinables et climatisation');
INSERT INTO types_autocar (nom, description) VALUES ('Luxe', 'Autocar de luxe avec sièges cuir et services premium');

-- Autocars
INSERT INTO autocars (numero_immatriculation, nombre_places, type_autocar_id) VALUES ('AB-123-CD', 50, 1);
INSERT INTO autocars (numero_immatriculation, nombre_places, type_autocar_id) VALUES ('EF-456-GH', 45, 2);
INSERT INTO autocars (numero_immatriculation, nombre_places, type_autocar_id) VALUES ('IJ-789-KL', 40, 3);

-- Emplacements pour les autocars
INSERT INTO emplacements (numero_siege, autocar_id) 
SELECT generate_series(1, 50), 1;

INSERT INTO emplacements (numero_siege, autocar_id) 
SELECT generate_series(1, 45), 2;

INSERT INTO emplacements (numero_siege, autocar_id) 
SELECT generate_series(1, 40), 3;

-- Voyages
INSERT INTO voyages (nom, description, duree, type_voyage, type_pension, hotel_id) 
VALUES ('Séjour à Paris', 'Découvrez la capitale française', 3, 'SEJOUR', 'DEMI_PENSION', 1);

INSERT INTO voyages (nom, description, duree, type_voyage, type_pension, hotel_id) 
VALUES ('Côte d''Azur Détente', 'Relaxez-vous sur la Côte d''Azur', 7, 'SEJOUR', 'PENSION_COMPLETE', 2);

INSERT INTO voyages (nom, description, duree, type_voyage, type_pension, hotel_id) 
VALUES ('Circuit Andalou', 'Explorez l''Andalousie authentique', 10, 'CIRCUIT', 'PENSION_COMPLETE', 3);

INSERT INTO voyages (nom, description, duree, type_voyage, type_pension, hotel_id) 
VALUES ('Art et Culture en Toscane', 'Immersion culturelle en Toscane', 5, 'CIRCUIT', 'DEMI_PENSION', 4);

-- Programmations
INSERT INTO programmations (date_depart, date_retour, prix_base, voyage_id) 
VALUES ('2024-07-15', '2024-07-18', 450.00, 1);

INSERT INTO programmations (date_depart, date_retour, prix_base, voyage_id) 
VALUES ('2024-08-01', '2024-08-08', 1200.00, 2);

INSERT INTO programmations (date_depart, date_retour, prix_base, voyage_id) 
VALUES ('2024-09-10', '2024-09-20', 1800.00, 3);

INSERT INTO programmations (date_depart, date_retour, prix_base, voyage_id) 
VALUES ('2024-06-20', '2024-06-25', 950.00, 4);

-- Association voyage-points de départ
INSERT INTO voyage_point_depart (voyage_id, point_depart_id) VALUES (1, 1);
INSERT INTO voyage_point_depart (voyage_id, point_depart_id) VALUES (1, 2);
INSERT INTO voyage_point_depart (voyage_id, point_depart_id) VALUES (2, 1);
INSERT INTO voyage_point_depart (voyage_id, point_depart_id) VALUES (3, 1);
INSERT INTO voyage_point_depart (voyage_id, point_depart_id) VALUES (4, 1);

-- Association programmation-autocar
INSERT INTO programmation_autocar (programmation_id, autocar_id) VALUES (1, 1);
INSERT INTO programmation_autocar (programmation_id, autocar_id) VALUES (2, 2);
INSERT INTO programmation_autocar (programmation_id, autocar_id) VALUES (3, 3);
INSERT INTO programmation_autocar (programmation_id, autocar_id) VALUES (4, 1);
