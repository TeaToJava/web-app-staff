CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS projects(
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
name VARCHAR(250) UNIQUE,
description TEXT);

CREATE TABLE IF NOT EXISTS department(
id UUID PRIMARY KEY DEFAULT gen_random_uuid() UNIQUE,
name VARCHAR(250));

CREATE TABLE IF NOT EXISTS employee(
id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
name VARCHAR(250),
department_id UUID,
	CONSTRAINT fk_department
      FOREIGN KEY(department_id) 
	  REFERENCES department(id)
	  ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS employee_projects(
employee_id UUID REFERENCES employee(id),
project_id UUID REFERENCES projects(id) ON DELETE CASCADE,
CONSTRAINT employee_projects_pk PRIMARY KEY(employee_id,project_id));

INSERT INTO department(name) VALUES ('Department 1'), ('Department 2');

INSERT INTO projects(name, description) 
VALUES ('Project 1', 'Web site for real estate'),
('Project 2', 'Mobile internet banking');

INSERT INTO employee(name) 
VALUES ('Employee 1'),
('Employee 2'), 
('Employee 3');

UPDATE employee
SET department_id = 
(SELECT id from department 
 WHERE name='Department 1')
 WHERE name = 'Employee 1';
 
 UPDATE employee
SET department_id = 
(SELECT id from department 
 WHERE name='Department 1')
 WHERE name = 'Employee 2';

 UPDATE employee
SET department_id = 
(SELECT id from department 
 WHERE name='Department 2')
 WHERE name = 'Employee 3';
 
 INSERT INTO employee_projects (employee_id, project_id)
 VALUES ((SELECT id FROM employee WHERE name = 'Employee 2'), 
		(SELECT id FROM projects WHERE name = 'Project 2'));
		
 INSERT INTO employee_projects (employee_id, project_id)
 VALUES ((SELECT id FROM employee WHERE name = 'Employee 1'), 
		(SELECT id FROM projects WHERE name = 'Project 1'));

