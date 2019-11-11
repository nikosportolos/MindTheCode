-- Populate database tables
INSERT INTO COMPANIES VALUES (1, 'UniSystems');
INSERT INTO BUSINESS_UNITS VALUES (1, 'Software Technical Division', 1);
INSERT INTO DEPARTMENTS VALUES (1, 'Resource Management Solutions & S/W Production Practices', 1);
INSERT INTO UNITS VALUES (1, 'Resource Management Solutions', 1);
INSERT INTO POSITIONS VALUES (1, 'Software Engineer', 1);
INSERT INTO POSITIONS VALUES (2, 'Data Analyst', 1);
INSERT INTO POSITIONS VALUES (3, 'Senior Consultant', 1);

INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Nikos', 'Portolos', 'Ermou 32', '6971123456', '2017-10-23', '1900-01-01', 1, 1, 1);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'John', 'Doe', 'Athinas 172', '6983216548', '2012-04-16', '1900-01-01', 1, 2, 3);
INSERT INTO EMPLOYEES (id, first_name, last_name, address, phone_number, hire_date, departure_date, status, contract_type, position_id) VALUES(hibernate_sequence.NEXTVAL, 'Sarah', 'James', 'Wall St.', '6947368142', '2010-07-10', '2018-11-01', 2, 1, 2);
