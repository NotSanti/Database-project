
-- Show all contributos and all their roles for a givin recording/compilation

CREATE OR REPLACE PROCEDURE showContributorsForSong
(thisrecordingID IN VARCHAR(24))
AS
BEGIN 
    SELECT CONCAT(fName, lName) AS contributor_name, role_name BULK COLLECT INTO allcontributors, allroles FROM Contributors
    INNER JOIN RolConSong ON Contributors.contributor_id = RolConSong.contributor_id
    INNER JOIN Roles ON RolesConSong.role_id = Roles.role_id
    INNER JOIN Song ON RolesConSong.song_id = Song.song_id
    WHERE song_id = thisrecordingID;
    dbms_output.put_line("Contributors: " || allcontributors);
    dbms_output.put_line("Roles: " || allroles)
END showContributorsForSong;

-- Show all details of a recording/compilation

SELECT *
FROM components
INNER JOIN song
USING(songid);



-- show all recoridng/compilatiosn a contributor may have been in and their role

CREATE OR REPLACE PROCEDURE showRoles
(contributorID IN VARCHAR(24))
AS
BEGIN
    SELECT song_id, role_name BULK COLLECT INTO this_songid, this_rolename FROM Contributors
    INNER JOIN RolConSong ON Contributors.contributor_id = RolConSong.contributor_id
    INNER JOIN Roles ON RolesConSong.role_id = Roles.role_id
    INNER JOIN Song ON RolesConSong.song_id = Song.song_id
    WHERE Song.contributor_id = contributorID;
    dbms_output.put_line('Song ID: ' || this_songid || ', Roles: ' || this_rolename);
END showRoles;