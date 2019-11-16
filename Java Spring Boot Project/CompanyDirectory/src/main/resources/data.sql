-- Populate Companies
INSERT INTO COMPANIES VALUES (1, 'UniSystems');

-- Populate Business Units
INSERT INTO BUSINESS_UNITS VALUES (1, 'Software Technical Division', 1);
INSERT INTO BUSINESS_UNITS VALUES (2, 'Financial', 1);

-- Populate Departments
INSERT INTO DEPARTMENTS VALUES (1, 'Resource Management Solutions & S/W Production Practices', 1);
INSERT INTO DEPARTMENTS VALUES (2, 'International Technical Department', 1);
INSERT INTO DEPARTMENTS VALUES (3, 'Application Management', 1);
INSERT INTO DEPARTMENTS VALUES (4, 'Credit Control Department', 2);

-- Populate Units
INSERT INTO UNITS VALUES (1, 'Resource Management Solutions', 1);
INSERT INTO UNITS VALUES (2, 'Recruiting Unit', 1);
INSERT INTO UNITS VALUES (3, 'Support', 3);

-- Populate Positions
INSERT INTO POSITIONS VALUES (1, 'Software Engineer', 1);
INSERT INTO POSITIONS VALUES (2, 'Data Analyst', 1);
INSERT INTO POSITIONS VALUES (3, 'Senior Consultant', 1);
INSERT INTO POSITIONS VALUES (4, 'First level Support', 3);
INSERT INTO POSITIONS VALUES (5, 'Field Engineer', 3);
INSERT INTO POSITIONS VALUES (6, 'Recruiter', 2);


-- Populate Employees
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Nikos', 'Portolos', 'Ermou 32', '6971123456', '2017-10-23', '1900-01-01', 1, 1, 1);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Nikoletta',  'Michopoulou',  'Athinas 172', '6983216548', '2012-04-16', '1900-01-01', 1, 1, 2);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Filippos',  'Kontos',  'Athinas 172', '6983216548', '2012-04-16', '1900-01-01', 1, 2, 3);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Dimitra',  'Fountzoula',  'Athinas 172', '6983216548', '2012-04-16', '1900-01-01', 1, 2, 1);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Michael',  'Stefanoudis',  'Athinas 172', '6983216548', '2012-04-16', '1900-01-01', 1, 2, 6);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'John', 'Doe', 'Athinas 172', '6983216548', '2012-04-16', '1900-01-01', 2, 0, 4);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Sarah', 'James', 'Wall St.', '6947368142', '2010-07-10', '2018-11-01', 1, 2, 5);