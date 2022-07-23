
INSERT INTO developers(name, age, sex, salary) VALUES
('Andrey', 25, 'male', 3000),
('Bogdan', 30, 'male', 2000),
('Denis', 35, 'male', 5000),
('Tanya', 30, 'female', 6000),
('Olya', 40, 'female', 3500),
('Ira', 50, 'female', 3400);

INSERT INTO skills (positions, skill_level) VALUES
('Java', 'Senior'),
('Java', 'Middle'),
('Java', 'Junior'),
('CSharp', 'Senior'),
('CSharp', 'Middle'),
('CSharp', 'Junior'),
('JS', 'Senior'),
('JS', 'Middle'),
('JS', 'Junior'),
('CPlus', 'Senior'),
('CPlus', 'Middle'),
('CPlus', 'Junior');

INSERT INTO projects (project_name, project_description, date_creation) VALUES
('Сhat bot','Creation and support of chat bots', '2005-05-25'),
('Sites', 'Website development', '2015-11-5'),
('Database', 'Creation and support of Database', '2020-7-24');

INSERT INTO companies(it_companies,company_description) VALUES
('Gamedev', 'Games development'),
('WebDev','Website development'),
('Mobile','Mobile development');


INSERT INTO developers_skills (developers_id, skills_id) VALUES
(2,3),
(1,2),
(4,5),
(3,8),
(5,8),
(6,4);

INSERT INTO developers_projects(developers_id, projects_id) VALUES
(1,1),
(2,1),
(3,2),
(4,2),
(5,3),
(6,3);