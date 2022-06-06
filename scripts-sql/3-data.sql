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
  (3, 'leonel', 'leonel', 'leonel@3il.fr' ),
  (4, 'alex', 'alex', 'alex@3il.fr'),
  (5, 'hoo44', 'hoo44', 'henri@per.fr');

ALTER TABLE compte ALTER COLUMN IdCompte RESTART WITH 6;

  
INSERT INTO role (IdCompte, Role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'UTILISATEUR' ),
  ( 2, 'PARENT' ),
  ( 3, 'NOUNOU' ),
  ( 4, 'NOUNOU' ),
  (5, 'PARENT');
  
  
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

INSERT INTO parent(Firstname,Lastname, Adresse, Phone, IdCompte) VALUES
('bruno','Le Grand','27 avenue louis remy','0605245469',2),
('henry','Marco','44 avenue du loir','0787897856',5);

INSERT INTO nounou(Firstname,Lastname, Adresse, Phone, IdCompte) VALUES
('leonel','TAGNE','23 rue de la mairie','0704265368',3),
('alex','TSAGUE','12 avenue des champs','0643567890',4);

INSERT INTO enfant(Firstname,Lastname, DateNaissance, IdParent) VALUES
('junior','Le Grand','2018-08-01',1),
('Frank','Le Grand','2016-12-02',1),
('Jerome','Marco','2020-03-23',2);

INSERT INTO contrat(dateDebut,dateFin,TarifHoraire,indemRepas,IdNounou,IdEnfant) VALUES
('2022-01-01','2023-01-01',15.55, 0.352,1,1),
('2022-01-01','2024-01-01',20, 0.352,2,2);

INSERT INTO garder(heureDebut,heureFin,aMange,dateJ, IdContrat) VALUES
('10:00:00','14:30:00',true,'2022-05-01',1),
('08:00:00','17:00:00',false,'2022-03-05',2);





