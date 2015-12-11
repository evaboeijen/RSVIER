/******************************************************************************/
/*           Generated by IBExpert 2015.11.18.1 10-12-2015 17:14:56           */
/******************************************************************************/

SET SQL DIALECT 3;

SET NAMES UTF8;

CONNECT 'rsvier' USER 'SYSDBA' PASSWORD 'masterkey';



/******************************************************************************/
/*                                 Generators                                 */
/******************************************************************************/

CREATE GENERATOR GEN_ADRES;
SET GENERATOR GEN_ADRES TO 2;

CREATE GENERATOR GEN_ARTIKEL;
SET GENERATOR GEN_ARTIKEL TO 1;

CREATE GENERATOR GEN_BESTELLING;
SET GENERATOR GEN_BESTELLING TO 1;

CREATE GENERATOR GEN_KLANT;
SET GENERATOR GEN_KLANT TO 3;



/******************************************************************************/
/*                                   Tables                                   */
/******************************************************************************/



CREATE TABLE ADRES (
    ADRES_ID    INTEGER NOT NULL,
    STRAATNAAM  VARCHAR(26) DEFAULT NULL,
    POSTCODE    VARCHAR(6) DEFAULT NULL,
    TOEVOEGING  VARCHAR(6) DEFAULT NULL,
    HUISNUMMER  VARCHAR(6) DEFAULT NULL,
    WOONPLAATS  VARCHAR(26) DEFAULT NULL,
    CREATED     TIMESTAMP default current_timestamp,
    UPDATED     TIMESTAMP default current_timestamp
);

CREATE TABLE ARTIKEL (
    ARTIKEL_ID     INTEGER NOT NULL,
    ARTIKEL_NAAM   VARCHAR(20) NOT NULL,
    ARTIKEL_PRIJS  FLOAT NOT NULL,
    CREATED        TIMESTAMP default current_timestamp,
    UPDATED        TIMESTAMP default current_timestamp
);

CREATE TABLE BESTELLING (
    BESTELLING_ID  INTEGER NOT NULL,
    KLANT_ID       INTEGER NOT NULL,
    CREATED        TIMESTAMP default current_timestamp,
    UPDATED        TIMESTAMP default current_timestamp
);

CREATE TABLE BESTELLING_ARTIKEL (
    BESTELLING_ID   INTEGER NOT NULL,
    ARTIKEL_ID      INTEGER NOT NULL,
    ARTIKEL_AANTAL  INTEGER NOT NULL,
    CREATED         TIMESTAMP default current_timestamp,
    UPDATED         TIMESTAMP default current_timestamp
);

CREATE TABLE KLANT (
    KLANT_ID       INTEGER NOT NULL,
    VOORNAAM       VARCHAR(50) NOT NULL,
    ACHTERNAAM     VARCHAR(51) NOT NULL,
    TUSSENVOEGSEL  VARCHAR(10) NOT NULL,
    EMAIL          VARCHAR(320) NOT NULL,
    CREATED        TIMESTAMP default current_timestamp,
    UPDATED        TIMESTAMP default current_timestamp
);

CREATE TABLE KLANT_ADRES (
    KLANT_ID  INTEGER NOT NULL,
    ADRES_ID  INTEGER NOT NULL,
    CREATED   TIMESTAMP default current_timestamp,
    UPDATED   TIMESTAMP default current_timestamp
);

INSERT INTO ADRES (ADRES_ID, STRAATNAAM, POSTCODE, TOEVOEGING, HUISNUMMER, WOONPLAATS, CREATED, UPDATED) VALUES (1, 'test3', '1212', '69', '12', 'amsterdam', '2015-12-10 16:42:06', '2015-12-10 17:03:19');
INSERT INTO ADRES (ADRES_ID, STRAATNAAM, POSTCODE, TOEVOEGING, HUISNUMMER, WOONPLAATS, CREATED, UPDATED) VALUES (2, 'test3', '6969', '69', '12', 'rotterdam', '2015-12-10 16:46:47', '2015-12-10 17:03:26');

COMMIT WORK;

INSERT INTO ARTIKEL (ARTIKEL_ID, ARTIKEL_NAAM, ARTIKEL_PRIJS, CREATED, UPDATED) VALUES (1, 'ipad air', 20.9899997711182, '2015-12-10 17:09:53', '2015-12-10 17:10:44');

COMMIT WORK;

INSERT INTO KLANT (KLANT_ID, VOORNAAM, ACHTERNAAM, TUSSENVOEGSEL, EMAIL, CREATED, UPDATED) VALUES (5, 'piet', 'puk', '-', 'bla@bla.com', '2015-12-10 16:48:45', '2015-12-10 17:10:12');
INSERT INTO KLANT (KLANT_ID, VOORNAAM, ACHTERNAAM, TUSSENVOEGSEL, EMAIL, CREATED, UPDATED) VALUES (3, 'pietje', 'test', '-', 'bla@bla.com', '2015-12-10 16:49:23', '2015-12-10 17:10:23');

COMMIT WORK;

INSERT INTO BESTELLING (BESTELLING_ID, KLANT_ID, CREATED, UPDATED) VALUES (1, 3, '2015-12-10 17:11:25', '2015-12-10 17:11:25');

COMMIT WORK;

INSERT INTO BESTELLING_ARTIKEL (BESTELLING_ID, ARTIKEL_ID, ARTIKEL_AANTAL, CREATED, UPDATED) VALUES (1, 1, 20, '2015-12-10 17:11:47', '2015-12-10 17:11:47');

COMMIT WORK;

INSERT INTO KLANT_ADRES (KLANT_ID, ADRES_ID, CREATED, UPDATED) VALUES (3, 2, '2015-12-10 17:12:19', '2015-12-10 17:12:19');

COMMIT WORK;



/******************************************************************************/
/*                                Primary keys                                */
/******************************************************************************/

ALTER TABLE ADRES ADD CONSTRAINT PK_ADRES PRIMARY KEY (ADRES_ID);
ALTER TABLE ARTIKEL ADD CONSTRAINT PK_ARTIKEL PRIMARY KEY (ARTIKEL_ID);
ALTER TABLE BESTELLING ADD CONSTRAINT PK_BESTELLING PRIMARY KEY (BESTELLING_ID);
ALTER TABLE BESTELLING_ARTIKEL ADD CONSTRAINT PK_BESTELLING_ARTIKEL PRIMARY KEY (BESTELLING_ID, ARTIKEL_ID);
ALTER TABLE KLANT ADD CONSTRAINT PK_KLANT PRIMARY KEY (KLANT_ID);
ALTER TABLE KLANT_ADRES ADD PRIMARY KEY (KLANT_ID, ADRES_ID);


/******************************************************************************/
/*                                Foreign keys                                */
/******************************************************************************/

ALTER TABLE BESTELLING ADD CONSTRAINT FK_BESTELLING_KLANT_ID FOREIGN KEY (KLANT_ID) REFERENCES KLANT (KLANT_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE BESTELLING_ARTIKEL ADD CONSTRAINT FK_ARTIKEL FOREIGN KEY (ARTIKEL_ID) REFERENCES ARTIKEL (ARTIKEL_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE BESTELLING_ARTIKEL ADD CONSTRAINT FK_BESTELLING FOREIGN KEY (BESTELLING_ID) REFERENCES BESTELLING (BESTELLING_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE KLANT_ADRES ADD CONSTRAINT FK_ADRES FOREIGN KEY (ADRES_ID) REFERENCES ADRES (ADRES_ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE KLANT_ADRES ADD CONSTRAINT FK_KLANT FOREIGN KEY (KLANT_ID) REFERENCES KLANT (KLANT_ID) ON DELETE CASCADE ON UPDATE CASCADE;


/******************************************************************************/
/*                                  Triggers                                  */
/******************************************************************************/



SET TERM ^ ;



/******************************************************************************/
/*                            Triggers for tables                             */
/******************************************************************************/



/* Trigger: TRIGGER_ADRES_ID */
CREATE TRIGGER TRIGGER_ADRES_ID FOR ADRES
ACTIVE BEFORE INSERT POSITION 0
AS
begin
if ( (new.ADRES_ID is null) or (new.ADRES_ID = 0) )
then new.ADRES_ID = gen_id(gen_adres, 1);
end
^

/* Trigger: TRIGGER_ARTIKEL_ID */
CREATE TRIGGER TRIGGER_ARTIKEL_ID FOR ARTIKEL
ACTIVE BEFORE INSERT POSITION 0
AS
begin
  if ( (new.artikel_ID is null) or (new.artikel_ID = 0) )
    then new.artikel_id = gen_id(gen_artikel, 1);
end
^

/* Trigger: TRIGGER_BESTELLING_ID */
CREATE TRIGGER TRIGGER_BESTELLING_ID FOR BESTELLING
ACTIVE BEFORE INSERT POSITION 0
AS
begin
  if ( (new.bestelling_ID is null) or (new.bestelling_ID = 0) )
    then new.bestelling_ID = gen_id(gen_bestelling,  1);
end
^

/* Trigger: TRIGGER_KLANT_ID */
CREATE TRIGGER TRIGGER_KLANT_ID FOR KLANT
ACTIVE BEFORE INSERT POSITION 0
AS
begin
    if ( (new.KLANT_ID is null) or (new.KLANT_ID = 0) )
    then new.KLANT_ID = gen_id(gen_klant, 1);
end
^

/* Trigger: UPD_TSTAMP_ADRES */
CREATE TRIGGER UPD_TSTAMP_ADRES FOR ADRES
ACTIVE BEFORE INSERT OR UPDATE POSITION 1000
AS
begin
  NEW.UPDATED=CURRENT_TIMESTAMP;
end
^

/* Trigger: UPD_TSTAMP_ARTIKEL */
CREATE TRIGGER UPD_TSTAMP_ARTIKEL FOR ARTIKEL
ACTIVE BEFORE INSERT OR UPDATE POSITION 1000
AS
begin
  NEW.UPDATED=CURRENT_TIMESTAMP;
end
^

/* Trigger: UPD_TSTAMP_BESTELLING */
CREATE TRIGGER UPD_TSTAMP_BESTELLING FOR BESTELLING
ACTIVE BEFORE INSERT OR UPDATE POSITION 1000
AS
begin
  NEW.UPDATED=CURRENT_TIMESTAMP;
end
^

/* Trigger: UPD_TSTAMP_BESTELLING_ARTIKEL */
CREATE TRIGGER UPD_TSTAMP_BESTELLING_ARTIKEL FOR BESTELLING_ARTIKEL
ACTIVE BEFORE INSERT POSITION 1000
AS
begin
  NEW.UPDATED=CURRENT_TIMESTAMP;
end
^

/* Trigger: UPD_TSTAMP_KLANT */
CREATE TRIGGER UPD_TSTAMP_KLANT FOR KLANT
ACTIVE BEFORE INSERT OR UPDATE POSITION 1000
AS
begin
  NEW.UPDATED=CURRENT_TIMESTAMP;
end
^

/* Trigger: UPD_TSTAMP_KLANT_ADRES */
CREATE TRIGGER UPD_TSTAMP_KLANT_ADRES FOR KLANT_ADRES
ACTIVE BEFORE INSERT OR UPDATE POSITION 1000
AS
begin
  NEW.UPDATED=CURRENT_TIMESTAMP;
end
^
SET TERM ; ^



/******************************************************************************/
/*                                Descriptions                                */
/******************************************************************************/

DESCRIBE TRIGGER TRIGGER_ADRES_ID
'Trigger voor auto_increment op adres_id in Adres tabel';

DESCRIBE TRIGGER TRIGGER_ARTIKEL_ID
'Trigger voor auto_increment op artikel_id in Artikel tabel';

DESCRIBE TRIGGER TRIGGER_BESTELLING_ID
'Trigger voor auto_increment op bestelling_id in Bestelling tabel';

DESCRIBE TRIGGER TRIGGER_KLANT_ID
'Trigger voor auto_increment op klant_id in Klant tabel';



/******************************************************************************/
/*                                Descriptions                                */
/******************************************************************************/

DESCRIBE GENERATOR GEN_ADRES
'Generator voor trigger adres_id in tabel Adres';

DESCRIBE GENERATOR GEN_ARTIKEL
'Generator voor trigger artikel_id in tabel Artikel';

DESCRIBE GENERATOR GEN_BESTELLING
'Generator voor trigger bestelling_id in tabel Bestelling';

DESCRIBE GENERATOR GEN_KLANT
'Generator voor trigger klant_id in tabel Klant';
