CREATE SEQUENCE rolesSeq
START WITH 1089
INCREMENT BY 1
MAXVALUE 9999;

CREATE OR REPLACE TRIGGER rolesTrigger
BEFORE INSERT ON roles
FOR EACH ROW
BEGIN
    IF :NEW.roleid IS NULL THEN
        SELECT 'RO' || rolesSeq.nextval INTO :NEW.roleid 
        FROM roles;
    END IF;
END;


CREATE SEQUENCE contributorSeq
START WITH 1644
INCREMENT BY 1
MAXVALUE 9999;

CREATE OR REPLACE TRIGGER contributorTrigger
BEFORE INSERT ON contributors
FOR EACH ROW
BEGIN
    IF :NEW.contributorid IS NULL THEN
        SELECT 'CO' || contributorSeq.nextval INTO :NEW.contributorid 
        FROM contributors;
    END IF;
END;


CREATE SEQUENCE songSeq
START WITH 10058
INCREMENT BY 1
MAXVALUE 99999;

CREATE OR REPLACE TRIGGER songTrigger
BEFORE INSERT ON song
FOR EACH ROW
BEGIN
    IF :NEW.songid IS NULL THEN
        SELECT 'S' || songSeq.nextval INTO :NEW.songid 
        FROM song;
    END IF;
END;


CREATE SEQUENCE componentSeq
START WITH 1007
INCREMENT BY 1
MAXVALUE 9999;

CREATE OR REPLACE TRIGGER componentTrigger
BEFORE INSERT ON components
FOR EACH ROW
BEGIN
    IF :NEW.componentid IS NULL THEN
        SELECT 'COM' || componentSeq.nextval INTO :NEW.componentid 
        FROM components;
    END IF;
END;


CREATE SEQUENCE marketSeq
START WITH 1005
INCREMENT BY 1
MAXVALUE 9999;

CREATE OR REPLACE TRIGGER marketTrigger
BEFORE INSERT ON markets
FOR EACH ROW
BEGIN
    IF :NEW.marketid IS NULL THEN
        SELECT 'M' || marketSeq.nextval INTO :NEW.marketid 
        FROM markets;
    END IF;
END;


CREATE SEQUENCE recordLabelSeq
START WITH 1042
INCREMENT BY 1
MAXVALUE 9999;

CREATE OR REPLACE TRIGGER recordLabelTrigger
BEFORE INSERT ON recordLabel
FOR EACH ROW
BEGIN
    IF :NEW.recordLabelid IS NULL THEN
        SELECT 'RL' || recordLabelSeq.nextval INTO :NEW.recordLabelid 
        FROM recordLabel;
    END IF;
END;







