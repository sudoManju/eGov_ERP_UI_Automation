INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP1')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP1'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP2')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP2'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP3')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP3'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP4')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP4'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP5')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP5'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP6')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP6'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP7')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP7'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP8')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP8'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP9')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP9'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP10')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP10'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP11')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP21'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP12')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP12'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP13')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP13'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP14')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP14'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP15')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP15'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP16')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP16'));

INSERT INTO eg_userrole (roleid, userid)
SELECT (select id from eg_role where name='Collection Operator'), (select id from eg_user where username='TP17')
WHERE
    NOT EXISTS (select roleid,userid from eg_userrole where roleid in (select id from eg_role where name='Collection Operator') and userid in 
  (select id from eg_user where username='TP17'));