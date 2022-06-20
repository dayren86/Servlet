
CREATE TABLE developers (
id SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
age VARCHAR(100) NOT NULL,
sex VARCHAR(100) NOT NULL
);

ALTER TABLE developers
ADD CONSTRAINT sex_enum_values
CHECK (sex IN ('male', 'female', 'unknown'));

CREATE TABLE skills (
id SERIAL PRIMARY KEY,
positions VARCHAR(100) NOT NULL,
skill_level VARCHAR(100) NOT NULL
);

ALTER TABLE skills
ADD CONSTRAINT skill_level_enum_values
CHECK (skill_level IN('Junior', 'Middle', 'Senior'));

ALTER TABLE skills
ADD CONSTRAINT position_enum_values
CHECK (positions IN('Java', 'Cplus', 'SSharp', 'JS'));

CREATE TABLE projects (
id SERIAL PRIMARY KEY,
project_name VARCHAR(100) NOT NULL,
project_description VARCHAR(100) NOT NULL
);

CREATE TABLE companies(
id SERIAL PRIMARY KEY,
it_companies VARCHAR(100) NOT NULL,
company_description VARCHAR(100) NOT NULL
);

CREATE TABLE customers(
id SERIAL PRIMARY KEY,
customers_it_companies VARCHAR(100) NOT NULL
);


CREATE TABLE developers_skills(
developers_id BIGINT NOT NULL,
skills_id BIGINT NOT NULL,
PRIMARY KEY (developers_id, skills_id),
FOREIGN KEY (developers_id) REFERENCES developers(id),
FOREIGN KEY (skills_id) REFERENCES skills(id)
);

CREATE TABLE developers_projects(
developers_id BIGINT NOT NULL,
projects_id BIGINT NOT NULL,
PRIMARY KEY (developers_id, projects_id),
FOREIGN KEY (developers_id) REFERENCES developers(id),
FOREIGN KEY (projects_id) REFERENCES projects(id)
);

CREATE TABLE companies_projects(
companies_id BIGINT NOT NULL,
projects_id BIGINT NOT NULL,
PRIMARY KEY (companies_id, projects_id),
FOREIGN KEY (companies_id) REFERENCES companies(id),
FOREIGN KEY (projects_id) REFERENCES projects(id)
);