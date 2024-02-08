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

