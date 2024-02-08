DO $$
BEGIN
 IF NOT EXISTS (SELECT 1 FROM pg_database 
				 WHERE datname = 'projectsdb') THEN
        EXECUTE 'CREATE DATABASE projectsdb';
		    END IF;
		    END $$;