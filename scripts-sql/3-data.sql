SET search_path TO nounous;


-- Supprime toutes les données
DELETE FROM telephone;
DELETE FROM personne;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM compte;


-- Insère les données

INSERT INTO compte (IdCompte, Pseudo, MotDePasse, Email ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr' ),
  (2, 'chef', 'chef', 'chef@3il.fr' ),
  (3, 'job', 'job', 'job@3il.fr' );

ALTER TABLE compte ALTER COLUMN IdCompte RESTART WITH 4;

  
INSERT INTO role (IdCompte, Role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'UTILISATEUR' ),
  ( 2, 'UTILISATEUR' ),
  ( 3, 'UTILISATEUR' );
  
  
INSERT INTO categorie (IdCategorie, Libelle ) VALUES 
  (1, 'Employés' ),
  (2, 'Partenaires' ),
  (3, 'Clients'),
  (4, 'Fournisseurs' ),
  (5, 'Dirigeants' );

ALTER TABLE categorie ALTER COLUMN IdCategorie RESTART WITH 6;
  
  
INSERT INTO personne (IdPersonne, IdCategorie, Nom, Prenom) VALUES 
  ( 1, 1, 'DESVALOIS', 'Christian' ),
  ( 2, 1, 'BELABDELLI', 'Fethi' ),
  ( 3, 1, 'AMBLARD', 'Emmanuel' );

ALTER TABLE personne ALTER COLUMN IdPersonne RESTART WITH 4;
  

INSERT INTO telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 11, 1, 'Portable', '06 11 11 11 11' ),
  ( 12, 1, 'Fax', '05 55 99 11 11' ),
  ( 13, 1, 'Bureau', '05 55 11 11 11' ),
  ( 21, 2, 'Portable', '06 22 22 22 22' ),
  ( 22, 2, 'Fax', '05 55 99 22 22' ),
  ( 23, 2, 'Bureau', '05 55 22 22 22' ),
  ( 31, 3, 'Portable', '06 33 33 33 33' ),
  ( 32, 3, 'Fax', '05 55 99 33 33' ),
  ( 33, 3, 'Bureau', '05 55 33 33 33' );

ALTER TABLE telephone ALTER COLUMN IdTelephone RESTART WITH 100;
 
