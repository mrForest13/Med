CREATE TABLE uzytkownik (
	uzytkownik_uz_id NUMBER NOT NULL, PRIMARY KEY (uzytkownik_uz_id),
	uzytkownik_user_type NUMBER NOT NULL,
	uzytkownik_firstname VARCHAR(50) NOT NULL,
	uzytkownik_lastname VARCHAR(50) NOT NULL,
	uzytkownik_login VARCHAR(20) NOT NULL,
	uzytkownik_user_password VARCHAR(20) NOT NULL
);

CREATE SEQUENCE UZYTKOWNIK_SEQ
   START WITH 17;

CREATE TABLE uzytkownik_obsluga (
  uzytkownik_obsluga_id NUMBER NOT NULL, PRIMARY KEY (uzytkownik_obsluga_id),
  uzytkownik_obsluga_uz_id NUMBER NOT NULL, FOREIGN KEY (uzytkownik_obsluga_uz_id) REFERENCES uzytkownik(uzytkownik_uz_id),
  uzytkownik_obsluga_hired_from DATE NOT NULL,
  uzytkownik_obsluga_hired_to DATE NOT NULL
);

CREATE TABLE uzytkownik_lab (
  uzytkownik_lab_id NUMBER NOT NULL, PRIMARY KEY (uzytkownik_lab_id),
  uzytkownik_lab_uz_id NUMBER NOT NULL, FOREIGN KEY (uzytkownik_lab_uz_id) REFERENCES uzytkownik(uzytkownik_uz_id),
  uzytkownik_lab_degree VARCHAR(40) not null,
  uzytkownik_lab_assistant CHAR(1) NOT NULL,
  uzytkownik_lab_blc CHAR(1) NOT NULL --uprawnienia do pobierania krwi
);

CREATE TABLE uzytkownik_pacjent (
	uzytkownik_pacjent_id NUMBER NOT NULL, PRIMARY KEY (uzytkownik_pacjent_id),
    uzytkownik_pacjent_uz_id NUMBER NOT NULL, FOREIGN KEY (uzytkownik_pacjent_uz_id) REFERENCES uzytkownik(uzytkownik_uz_id),
	uzytkownik_pacjent_pesel VARCHAR(13) NOT NULL,
	uzytkownik_pacjent_email VARCHAR(100) NOT NULL,
	uzytkownik_pacjent_phone VARCHAR(30) NOT NULL,
	uzytkownik_pacjent_birthday DATE NOT NULL,
	uzytkownik_pacjent_sex CHAR(1) NOT NULL
);

CREATE SEQUENCE UZYTKOWNIK_P_SEQ
   START WITH 6;
  
CREATE TABLE uzytkownik_doktor (
	uzytkownik_doktor_id NUMBER NOT NULL, PRIMARY KEY (uzytkownik_doktor_id),
	uzytkownik_doktor_uz_id NUMBER NOT NULL, FOREIGN KEY (uzytkownik_doktor_uz_id) REFERENCES uzytkownik(uzytkownik_uz_id),
	uzytkownik_doktor_spec VARCHAR(40) not null, --specjalizacja lekarza
	uzytkownik_doktor_degree VARCHAR(40) not null
);
CREATE TABLE recepta(
	recepta_id NUMBER NOT NULL, PRIMARY KEY(recepta_id),
	recepta_uzytkownik_uz_id NUMBER NOT NULL, FOREIGN KEY (recepta_uzytkownik_uz_id) REFERENCES uzytkownik(uzytkownik_uz_id),
	recepta_uzytkownik_doktor_id NUMBER NOT NULL, FOREIGN KEY (recepta_uzytkownik_doktor_id) REFERENCES uzytkownik_doktor(uzytkownik_doktor_id), 
	recepta_date_of_issue DATE NOT NULL,
	recepta_additional_rigths CHAR(1) NOT NULL
);

--poszczegolne leki w recepcie
CREATE TABLE lek(
	lek_id NUMBER NOT NULL, PRIMARY KEY(lek_id),
	lek_recepta_id NUMBER NOT NULL, FOREIGN KEY (lek_recepta_id) REFERENCES recepta(recepta_id),
	lek_name VARCHAR(200) not null,
	lek_refund NUMBER not null --% refundacji
);

--dostepne rodzaje badan / wizyt
CREATE TABLE visit_types(
visit_type_id NUMBER NOT NULL, PRIMARY KEY(visit_type_id),
visit_type VARCHAR(100)
);

CREATE TABLE visit(
visit_id NUMBER NOT NULL, PRIMARY KEY(visit_id),
visit_uzytkownik_doktor_id NUMBER NOT NULL, FOREIGN KEY (visit_uzytkownik_doktor_id) REFERENCES uzytkownik_doktor(uzytkownik_doktor_id),
visit_visit_types_id NUMBER NOT NULL, FOREIGN KEY (visit_visit_types_id) REFERENCES visit_types(visit_type_id),
visit_user_id NUMBER, FOREIGN KEY (visit_user_id) REFERENCES uzytkownik(uzytkownik_uz_id),
visit_date_from DATE,
visit_date_to DATE,
visit_price NUMBER,
visit_is_confirmed CHAR(1)
);
--wyniki badan
CREATE TABLE lab(
lab_id NUMBER NOT NULL, PRIMARY KEY(lab_id),
lab_uzytkownik_uz_id NUMBER NOT NULL, FOREIGN KEY (lab_uzytkownik_uz_id) REFERENCES uzytkownik(uzytkownik_uz_id),
lab_date DATE NOT NULL,
lab_file BLOB --zapisywanie wygenerowanego pdfa?
);

CREATE TABLE sample(
sample_id NUMBER NOT NULL, PRIMARY KEY(sample_id),
sample_lab_id NUMBER NOT NULL, FOREIGN KEY (sample_lab_id) REFERENCES lab(lab_id),
sample_name VARCHAR(200) NOT NULL,
sample_result VARCHAR(200) NOT NULL,
sample_standard_positive VARCHAR(10),
sample_standard_negative VARCHAR(10),
sample_unit VARCHAR(20) NOT NULL,
sample_description VARCHAR(500));

CREATE TABLE uzytkownik_session(
session_id NUMBER NOT NULL, PRIMARY KEY(session_id),
session_uzytkownik_uz_id NUMBER NOT NULL, FOREIGN KEY (session_uzytkownik_uz_id) REFERENCES uzytkownik(uzytkownik_uz_id),
session_hash VARCHAR(20) NOT NULL,
session_date DATE,
session_is_active CHAR(1)
);

/*
drop table uzytkownik_session;
drop table sample;
drop table lab;
drop table visit;
drop table visit_types;
drop table lek;
drop table recepta;
drop table uzytkownik_doktor;
drop table uzytkownik_lab;
drop table uzytkownik_obsluga;
drop table uzytkownik_pacjent;
drop table uzytkownik;

DROP SEQUENCE UZYTKOWNIK_SEQ;
DROP SEQUENCE UZYTKOWNIK_P_SEQ;
*/