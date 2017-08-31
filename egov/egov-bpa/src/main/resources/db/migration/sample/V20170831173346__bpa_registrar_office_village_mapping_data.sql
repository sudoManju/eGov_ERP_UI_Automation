-----------------------registrar office and village mapping---------------------------------------------

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Feroke'), (select id from eg_boundary where name ='Beypore' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chalappuram'), (select id from eg_boundary where name ='Beypore' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Meenchantha'), (select id from eg_boundary where name ='Beypore' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chevayoor'), (select id from eg_boundary where name ='Chelavoor' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Kakkodi'), (select id from eg_boundary where name ='Chelavoor' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chalappuram'), (select id from eg_boundary where name ='Cheruvannur' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Meenchantha'), (select id from eg_boundary where name ='Cheruvannur' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chevayoor'), (select id from eg_boundary where name ='Chevayoor' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Kakkodi'), (select id from eg_boundary where name ='Elathur' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Westhill'), (select id from eg_boundary where name ='Elathur' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Westhill'), (select id from eg_boundary where name ='Kacheri' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Kozhikode'), (select id from eg_boundary where name ='Kasaba' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chevayoor'), (select id from eg_boundary where name ='Kottoli' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chalappuram'), (select id from eg_boundary where name ='Nagaram' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chevayoor'), (select id from eg_boundary where name ='Nellikkode' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chalappuram'), (select id from eg_boundary where name ='Panniyankara' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Meenchantha'), (select id from eg_boundary where name ='Panniyankara' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Westhill'), (select id from eg_boundary where name ='Puthiyangadi' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Chalappuram'), (select id from eg_boundary where name ='Valayanad' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());

INSERT INTO egbpa_mstr_registrar_village( id, registrarOffice, village, isactive, version, createdby, createddate, lastmodifiedby, lastmodifieddate)
	VALUES (nextval('seq_egbpa_mstr_registrar_village'), (select id from egbpa_mstr_registrar_office where name ='Kakkodi'), (select id from eg_boundary where name ='Vengeri' and boundarytype=(select id from eg_boundary_type where name='Locality')), true, 0, 1, now(), 1, now());