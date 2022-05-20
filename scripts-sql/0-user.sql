

-- Supprime le schéma nounous

DROP SCHEMA IF EXISTS nounous CASCADE;


-- Crée l'utilisateur nounous
-- (après l'avoir supprimé au préalable s'il existait déjà)

DO $code$
BEGIN
	IF EXISTS (SELECT  FROM pg_catalog.pg_roles WHERE rolname  = 'nounous')
	THEN
		REVOKE CREATE ON DATABASE postgres FROM nounous;
		DROP USER nounous;
	END IF;
END
$code$;

CREATE USER nounous WITH PASSWORD 'nounous';
GRANT CREATE ON DATABASE postgres TO nounous;

