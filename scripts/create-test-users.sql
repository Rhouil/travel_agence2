-- Script pour créer les utilisateurs de test directement en base
USE travel_agency_db;

-- Supprimer les utilisateurs existants s'ils existent
DELETE FROM user_roles WHERE user_id IN (SELECT id FROM users WHERE username IN ('admin', 'client'));
DELETE FROM users WHERE username IN ('admin', 'client');

-- Créer les utilisateurs avec les bonnes données
INSERT INTO users (username, password, email, first_name, last_name, phone, gender, ville_id) 
VALUES ('admin', 'admin123', 'admin@travel.com', 'Admin', 'System', '0123456789', 'HOMME', NULL);

INSERT INTO users (username, password, email, first_name, last_name, phone, gender, ville_id) 
VALUES ('client', 'client123', 'client@travel.com', 'Jean', 'Dupont', '0987654321', 'HOMME', NULL);

-- Ajouter les rôles
INSERT INTO user_roles (user_id, roles) 
SELECT id, 'ADMIN' FROM users WHERE username = 'admin';

INSERT INTO user_roles (user_id, roles) 
SELECT id, 'CLIENT' FROM users WHERE username = 'client';

-- Vérifier les données créées
SELECT 
    u.id,
    u.username, 
    u.password, 
    u.email,
    u.first_name,
    u.last_name,
    ur.roles 
FROM users u 
LEFT JOIN user_roles ur ON u.id = ur.user_id 
WHERE u.username IN ('admin', 'client')
ORDER BY u.username, ur.roles;
