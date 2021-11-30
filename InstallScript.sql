DROP TABLE Components;
DROP TABLE Distribution;
DROP TABLE RolesConSong;
DROP TABLE AuditLog;
DROP TABLE RecordLabel;
DROP TABLE Markets;
DROP TABLE Contributors;
DROP TABLE Song;
DROP TABLE Roles;

-- All time aspects will be in seconds --

CREATE TABLE Roles (
    roleId VARCHAR2(20) PRIMARY KEY,
    roleName VARCHAR2(20)
);

CREATE TABLE Contributors (
    contributorId VARCHAR2(20) PRIMARY KEY,
    fName VARCHAR(20),
    lName VARCHAR(20)
);

CREATE TABLE Song (
    songId VARCHAR2(20) PRIMARY KEY,
    duration NUMBER(6)
);

CREATE TABLE Markets (
    marketId VARCHAR(20) PRIMARY KEY,
    area VARCHAR2(20)
);

CREATE TABLE RecordLabel (
    recordLabelId VARCHAR2(20) PRIMARY KEY,
    name VARCHAR2(20)
);

CREATE TABLE AuditLog (
    userName VARCHAR2(20) PRIMARY KEY,
    dateInfo Date,
    information VARCHAR2(100)
);
    
CREATE TABLE RolesConSong (
    contributorId VARCHAR(20),
    roleId VARCHAR2(20),
    songId VARCHAR2(20),
    FOREIGN KEY (roleId) REFERENCES Roles(roleId),
    FOREIGN KEY (songId) REFERENCES Song(songId),
    FOREIGN KEY (contributorId) REFERENCES Contributors(contributorId),
    PRIMARY KEY(contributorId,roleId,songId)
);


CREATE TABLE Components (
    componentId VARCHAR(20) PRIMARY KEY,
    songId VARCHAR(20),
    offsetComponent NUMBER(10),
    durationComponent NUMBER(10),
    component# VARCHAR2(20),
    offsetSong NUMBER(10),
    durationSong NUMBER(10),
    FOREIGN KEY (songId) REFERENCES Song(songId)
);

CREATE TABLE Distribution (
    songId VARCHAR(20),
    recordLabelId VARCHAR2(20),
    marketId VARCHAR2(20),
    releaseDate Date,
    FOREIGN KEY (songId) REFERENCES Song(songId),
    FOREIGN KEY (recordLabelId) REFERENCES RecordLabel(recordLabelId),
    FOREIGN KEY (marketId) REFERENCES Markets(marketId)
);


    
    
    
    

