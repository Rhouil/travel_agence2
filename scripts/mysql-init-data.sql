-- Script d'initialisation pour MySQL
-- Création de la base de données (optionnel si createDatabaseIfNotExist=true)
CREATE DATABASE IF NOT EXISTS travel_agency_db;
USE travel_agency_db;

-- Insertion des données de base pour l'agence de voyage

-- Pays
INSERT INTO pays (nom) VALUES ('France');
INSERT INTO pays (nom) VALUES ('Espagne');
INSERT INTO pays (nom) VALUES ('Italie');

-- Régions
INSERT INTO regions (nom, pays_id) VALUES ('Île-de-France', 1);
INSERT INTO regions (nom, pays_id) VALUES ('Provence-Alpes-Côte d\'Azur', 1);
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

-- Emplacements pour l'autocar 1 (50 places)
INSERT INTO emplacements (numero_siege, autocar_id) VALUES 
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1),
(11, 1), (12, 1), (13, 1), (14, 1), (15, 1), (16, 1), (17, 1), (18, 1), (19, 1), (20, 1),
(21, 1), (22, 1), (23, 1), (24, 1), (25, 1), (26, 1), (27, 1), (28, 1), (29, 1), (30, 1),
(31, 1), (32, 1), (33, 1), (34, 1), (35, 1), (36, 1), (37, 1), (38, 1), (39, 1), (40, 1),
(41, 1), (42, 1), (43, 1), (44, 1), (45, 1), (46, 1), (47, 1), (48, 1), (49, 1), (50, 1);

-- Emplacements pour l'autocar 2 (45 places)
INSERT INTO emplacements (numero_siege, autocar_id) VALUES 
(1, 2), (2, 2), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2), (10, 2),
(11, 2), (12, 2), (13, 2), (14, 2), (15, 2), (16, 2), (17, 2), (18, 2), (19, 2), (20, 2),
(21, 2), (22, 2), (23, 2), (24, 2), (25, 2), (26, 2), (27, 2), (28, 2), (29, 2), (30, 2),
(31, 2), (32, 2), (33, 2), (34, 2), (35, 2), (36, 2), (37, 2), (38, 2), (39, 2), (40, 2),
(41, 2), (42, 2), (43, 2), (44, 2), (45, 2);

-- Emplacements pour l'autocar 3 (40 places)
INSERT INTO emplacements (numero_siege, autocar_id) VALUES 
(1, 3), (2, 3), (3, 3), (4, 3), (5, 3), (6, 3), (7, 3), (8, 3), (9, 3), (10, 3),
(11, 3), (12, 3), (13, 3), (14, 3), (15, 3), (16, 3), (17, 3), (18, 3), (19, 3), (20, 3),
(21, 3), (22, 3), (23, 3), (24, 3), (25, 3), (26, 3), (27, 3), (28, 3), (29, 3), (30, 3),
(31, 3), (32, 3), (33, 3), (34, 3), (35, 3), (36, 3), (37, 3), (38, 3), (39, 3), (40, 3);

-- Voyages
INSERT INTO voyages (nom, description, duree, type_voyage, type_pension, hotel_id) 
VALUES ('Séjour à Paris', 'Découvrez la capitale française', 3, 'SEJOUR', 'DEMI_PENSION', 1);

INSERT INTO voyages (nom, description, duree, type_voyage, type_pension, hotel_id) 
VALUES ('Côte d\'Azur Détente', 'Relaxez-vous sur la Côte d\'Azur', 7, 'SEJOUR', 'PENSION_COMPLETE', 2);

INSERT INTO voyages (nom, description, duree, type_voyage, type_pension, hotel_id) 
VALUES ('Circuit Andalou', 'Explorez l\'Andalousie authentique', 10, 'CIRCUIT', 'PENSION_COMPLETE', 3);

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

-- Utilisateurs de test (mots de passe non chiffrés pour les tests)
INSERT INTO users (username, password, email, first_name, last_name, phone, gender, ville_id) 
VALUES ('admin', 'admin123', 'admin@travel.com', 'Admin', 'System', '0123456789', 'HOMME', 1);

INSERT INTO users (username, password, email, first_name, last_name, phone, gender, ville_id) 
VALUES ('client1', 'client123', 'client1@email.com', 'Jean', 'Dupont', '0987654321', 'HOMME', 1);

INSERT INTO users (username, password, email, first_name, last_name, phone, gender, ville_id) 
VALUES ('client2', 'client123', 'client2@email.com', 'Marie', 'Martin', '0147258369', 'FEMME', 2);

-- Rôles des utilisateurs
INSERT INTO user_roles (user_id, roles) VALUES (1, 'ADMIN');
INSERT INTO user_roles (user_id, roles) VALUES (2, 'CLIENT');
INSERT INTO user_roles (user_id, roles) VALUES (3, 'CLIENT');
