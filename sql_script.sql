drop database projektUrlop;
create database projektUrlop;

use projektUrlop;


create table daneUrlopu (
id int primary key auto_increment,
imieNazwisko varchar(100),
od varchar(100),
doU varchar(100),
iloscDni int,
statusU varchar(100)
);

create table dane_logowania(
id_uzytkownika varchar(100)  primary key ,
haslo varchar(100),
email varchar(100)
);


-- Table urlopydb.`okresy_nauki`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `okresy_nauki` ;

CREATE TABLE IF NOT EXISTS `okresy_nauki` (
  id_szkoly VARCHAR(10) NOT NULL,
  nazwa_szkoly VARCHAR(150) NULL,
  dodatkowy_okres VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_szkoly))
ENGINE = InnoDB;


DROP TABLE IF EXISTS `okres_zatrudnienia_w_poprzednim_miejscu_pracy` ;

CREATE TABLE IF NOT EXISTS `okres_zatrudnienia_w_poprzednim_miejscu_pracy` (
  id_pracownika INT NOT NULL,
  okres_zatrudnienia VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_pracownika))
ENGINE = InnoDB;

DROP TABLE IF EXISTS`daty_zatrudnienia` ;

CREATE TABLE IF NOT EXISTS `daty_zatrudnienia` (
  id_pracownika INT NOT NULL,
  data_zatrudnienia DATE NOT NULL,
  PRIMARY KEY (id_pracownika))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `okresy_urlopu` ;

CREATE TABLE IF NOT EXISTS `okresy_urlopu` (
  zakres_przepracowany INT NOT NULL,
  przyslugujacy_okres INT NULL,
  PRIMARY KEY (zakres_przepracowany))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `przyznane_urlopy` ;

CREATE TABLE IF NOT EXISTS `przyznane_urlopy` (
  id_uzytkownika INT NOT NULL,
  poczatek DATE NULL,
  koniec DATE NULL,
  id_pracownika VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_uzytkownika, id_pracownika))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `zapytania_o_urlopy` ;

CREATE TABLE IF NOT EXISTS `zapytania_o_urlopy` (
  id_uzytkownika INT NOT NULL,
  poczatek DATE NULL,
  koniec DATE NULL,
  id_pracownika VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_uzytkownika, id_pracownika))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `odrzucone_urlopy` ;

CREATE TABLE IF NOT EXISTS `odrzucone_urlopy` (
  id_uzytkownika INT NOT NULL,
  poczatek DATE NULL,
  koniec DATE NULL,
  id_pracownika VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_uzytkownika, id_pracownika))
ENGINE = InnoDB;



 -- CREATE USER 'pracownik'@'localhost';
 --  SET PASSWORD FOR 'pracownik'@'localhost' = 'pracownik';
  
--   CREATE USER 'kierownik'@'localhost';
--   SET PASSWORD FOR 'kierownik'@'localhost' = 'kierownik';
--   
--   
-- grant ALL PRIVILEGES on projektUrlop.* to 'kierownik'@'localhost';
-- grant INSERT , SELECT , UPDATE on projektUrlop.* to 'pracownik'@'localhost';

-- drop trigger poDodaniu;

INSERT INTO daneUrlopu(imieNazwisko,od,doU,iloscDni,statusU) 
                    VALUES("Mateusz","2020-09-21","2020-09-28",8,"nie zatwierdzone");

INSERT INTO daneUrlopu(imieNazwisko,od,doU,iloscDni,statusU) 
                    VALUES("ada","2020-09-21","2020-09-28",8,"");

insert into daty_zatrudnienia(id_pracownika, data_zatrudnienia) values (
"1", "2001-01-01");
insert into daty_zatrudnienia(id_pracownika, data_zatrudnienia) values (
"2", "2002-02-02");
insert into daty_zatrudnienia(id_pracownika, data_zatrudnienia) values (
"3", "2003-03-03");

insert into okres_zatrudnienia_w_poprzednim_miejscu_pracy(id_pracownika, okres_zatrudnienia) values (
"1", 2);
insert into okres_zatrudnienia_w_poprzednim_miejscu_pracy(id_pracownika, okres_zatrudnienia) values (
"2", 2);
insert into okres_zatrudnienia_w_poprzednim_miejscu_pracy(id_pracownika, okres_zatrudnienia) values (
"3", 2);

-- wszystkie mozliwe okresy za skzole zosyaly tu zawarte -- 
insert into okresy_nauki(id_szkoly, nazwa_szkoly, dodatkowy_okres) values (
"1", 
"zasadnicza lub inna równorzędna szkoła zawodowa, dla której czas trwania nauki  jest przewidziany programem nauczania",
"3"
);
insert into okresy_nauki(id_szkoly, nazwa_szkoly, dodatkowy_okres) values (
"3", 
"średnia szkoła zawodowa dla absolwentów zasadniczych (równorzędnych) szkół zawodowych",
"5"
);
insert into okresy_nauki(id_szkoly, nazwa_szkoly, dodatkowy_okres) values (
"2", 
"średnia szkoła zawodowa, dla której czas trwania nauki jest przewidziany programem nauczania",
"5"
);
insert into okresy_nauki(id_szkoly, nazwa_szkoly, dodatkowy_okres) values (
"4", 
"średnia szkoła ogólnokształcąca",
"4"
);
insert into okresy_nauki(id_szkoly, nazwa_szkoly, dodatkowy_okres) values (
"5", 
"szkoła policealna",
"6"
);
insert into okresy_nauki(id_szkoly, nazwa_szkoly, dodatkowy_okres) values (
"6", 
"szkoła wyższa",
"8"
);


insert into dane_logowania(id_uzytkownika, email, haslo) values (
"1", "lg1", "pg1");
insert into dane_logowania(id_uzytkownika, email, haslo) values (
"2", "lg2", "pg2");
insert into dane_logowania(id_uzytkownika, email, haslo) values (
"3", "lg3", "pg3");
