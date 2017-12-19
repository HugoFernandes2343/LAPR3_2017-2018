CREATE OR REPLACE PACKAGE pkgSailors AS 
  TYPE ref_cursor IS REF CURSOR; 
END pkgSailors;
/

CREATE OR REPLACE FUNCTION getSailor(sid NUMBER)
RETURN pkgSailors.ref_cursor
AS
  curSailor pkgSailors.ref_cursor;
BEGIN
  OPEN curSailor FOR SELECT * FROM sailors WHERE sailors.sid = getSailor.sid; 
  RETURN curSailor;
END;
/

CREATE OR REPLACE FUNCTION boatsColor(color VARCHAR)
RETURN pkgsailors.ref_cursor
AS
  curBoats pkgsailors.ref_cursor;
BEGIN
  OPEN curBoats FOR SELECT * FROM boats WHERE boats.color = boatsColor.color;
  RETURN curBoats;        
END;
/

CREATE OR REPLACE PROCEDURE addSailor(sid NUMBER, sname VARCHAR, rating NUMBER, age NUMBER) 
AS
BEGIN
  INSERT INTO sailors VALUES(sid,sname,rating,age);   
END;
/

CREATE OR REPLACE PROCEDURE removeSailor(sid NUMBER) 
IS
BEGIN
  DELETE FROM sailors WHERE sailors.sid = removeSailor.sid;   
END;
/