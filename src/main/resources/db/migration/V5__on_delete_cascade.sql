ALTER TABLE developers_skills
DROP CONSTRAINT developers_skills_developers_id_fkey;

ALTER TABLE developers_projects
DROP CONSTRAINT developers_projects_developers_id_fkey;


ALTER TABLE IF EXISTS public.developers_skills
    ADD CONSTRAINT developers_skills_developers_id_fkey FOREIGN KEY (developers_id)
    REFERENCES public.developers (id)
	ON DELETE CASCADE;

ALTER TABLE IF EXISTS public.developers_projects
    ADD CONSTRAINT developers_projects_developers_id_fkey FOREIGN KEY (developers_id)
    REFERENCES public.developers (id)
	ON DELETE CASCADE;