
-- Show all contributos and all their roles for a givin recording/compilation
CREATE OR REPLACE TYPE vcon IS VARRAY(1000) OF VARCHAR2(60);


CREATE OR REPLACE PROCEDURE showContributorsForSong(thisrecordingID IN song.songid%TYPE)
AS
    allcontributors vcon;
    allroles vcon;
BEGIN 
    SELECT fullname , rolename BULK COLLECT INTO allcontributors, allroles FROM Contributors
    INNER JOIN RolesConSong USING(contributorid)
    INNER JOIN Roles USING(roleid)
    INNER JOIN Song USING(songid)
    WHERE songid = thisrecordingID;

   FOR i in 1 .. allcontributors.count
   LOOP
    dbms_output.put_line('Contributors: ' || allcontributors(i));
    dbms_output.put_line('Roles: ' || allroles(i));
    END LOOP;
END showContributorsForSong;

EXECUTE showContributorsForSong('S10002');


-- Show all details of a recording/compilation

SELECT *
FROM components
INNER JOIN song
USING(songid);

 

-- show all recoridng/compilatiosn a contributor may have been in and their role

CREATE OR REPLACE PROCEDURE showRoles (conID IN contributors.contributorid%TYPE)
AS
    vsongid vcon;
    vrolename vcon;
BEGIN
    
    SELECT s.songid, r.rolename BULK COLLECT INTO vsongid, vrolename FROM roles r
    INNER JOIN rolesconsong rcs ON r.roleid = rcs.roleid
    INNER JOIN contributors c ON rcs.contributorid = c.contributorid
    INNER JOIN song s ON rcs.songid = s.songid
    WHERE rcs.contributorid =  conID;
    
     FOR i in 1 .. vsongid.count
   LOOP
    dbms_output.put_line('Song ID: ' || vsongid(i) || ', Roles: ' || vrolename(i));
    END LOOP;
    
END showRoles;

EXECUTE showRoles('CO1165');

SELECT s.songid, r.rolename FROM Contributors c
    INNER JOIN RolesConSong rcs ON c.contributorid = rcs.contributorid
    INNER JOIN Roles r ON rcs.roleid = r.roleid
    INNER JOIN Song s ON rcs.songid = s.songid
    WHERE rcs.contributorid = 'CO1165';
    
SELECT s.songid, r.rolename FROM roles r
INNER JOIN rolesconsong rcs ON r.roleid = rcs.roleid
INNER JOIN contributors c ON rcs.contributorid = c.contributorid
INNER JOIN song s ON rcs.songid = s.songid
WHERE rcs.contributorid = 'CO1165';