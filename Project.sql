-- Question #1 (Santiago)
-- Show all contributos and all their roles for a givin recording/compilation


-- Question #2 (Matteo)
-- Show all details of a recording/compilation
SELECT *
FROM components
INNER JOIN song
USING(songid);


-- Question #3 (Oleks)
-- show all recoridng/compilatiosn a contributor may have been in and their role