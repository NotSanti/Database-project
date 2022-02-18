Authors: Santiago Luna, Matteo Robidoux, Oleksandr Sologub

This a mock databse for a recording studio. It's a complete application built on Java, using oracle as the databse. The user can interact with the database through the command line, with a full CRUD.


Run Instructions:

SQL: In the SQL folder, you must first the DeletScript SQL file to make sure there are no tables already created and start on a clean slate,
you must then run InstallScript which sets up all the tables and sequences and inserts the data within the tables.

Java: In the JDBC folder, you must run the Application java file and will ask for your username and password, if valid, it will present you
a number of choices to choose between to either INSERT, UPDATE, or DELETE within the database, and it will then ask you which table and the values needed to continue with this procedure.
