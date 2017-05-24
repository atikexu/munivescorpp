--## Data de Cabecera
CREATE TABLE importdbvalor
(
  id serial NOT NULL,
  cdd_alt character varying(255),
  cdd_bas character varying(255),
  cod_cli character varying(255),
  cod_mot character varying(255),
  dep_alt character varying(255),
  dep_bas character varying(255),
  des_doc character varying(255),
  des_emi character varying(255),
  des_est character varying(255),
  des_mot character varying(255),
  des_sit character varying(255),
  des_vin character varying(255),
  destino character varying(255),
  dir_alt character varying(255),
  dir_bas character varying(255),
  doc_ide character varying(255),
  fec_ent character varying(255),
  fec_env character varying(255),
  fec_hoj character varying(255),
  fec_rcc character varying(255),
  ind_emi character varying(255),
  ind_est character varying(255),
  ind_sit character varying(255),
  ind_vin character varying(255),
  nom_cli character varying(255),
  nro_env character varying(255),
  nro_hoj character varying(255),
  nro_man character varying(255),
  nro_rcc character varying(255),
  nro_ref character varying(255),
  per_rcc character varying(255),
  pos_alt character varying(255),
  pos_bas character varying(255),
  ref_alt character varying(255),
  ref_bas character varying(255),
  tip_doc character varying(255),
  titular character varying(255),
  tlf_001 character varying(255),
  tlf_002 character varying(255),
  ubi_alt character varying(255),
  ubi_bas character varying(255),
  fin character varying(250)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE importdbvalor
  OWNER TO nasqa;

SET client_encoding to 'UTF-8';
COPY importdbvalor(cdd_alt,cdd_bas,cod_cli,cod_mot,dep_alt,dep_bas,des_doc,
des_emi,des_est,des_mot,des_sit,des_vin,destino,dir_alt,dir_bas,doc_ide,
fec_ent,fec_env,fec_hoj,fec_rcc,ind_emi,ind_est,ind_sit,ind_vin,nom_cli,
nro_env,nro_hoj,nro_man,nro_rcc,nro_ref,per_rcc,pos_alt,pos_bas,ref_alt,ref_bas,
tip_doc,titular,tlf_001,tlf_002,ubi_alt,ubi_bas,fin) FROM '/home/webgeod/Documentos/CAB201502210001' 
DELIMITER '|' ;

--SET client_encoding to 'ISO-8859-1';
CREATE OR REPLACE FUNCTION LOAD_DATA_CAB(pathFile character varying) RETURNS INTEGER AS $$
DECLARE STATEMENT TEXT;
BEGIN
STATEMENT := '
DELETE FROM importdbvalor WHERE 1=1;
SET client_encoding to ''UTF-8'';
COPY importdbvalor(cdd_alt,cdd_bas,cod_cli,cod_mot,dep_alt,dep_bas,des_doc,
des_emi,des_est,des_mot,des_sit,des_vin,destino,dir_alt,dir_bas,doc_ide,
fec_ent,fec_env,fec_hoj,fec_rcc,ind_emi,ind_est,ind_sit,ind_vin,nom_cli,
nro_env,nro_hoj,nro_man,nro_rcc,nro_ref,per_rcc,pos_alt,pos_bas,ref_alt,ref_bas,
tip_doc,titular,tlf_001,tlf_002,ubi_alt,ubi_bas,fin) FROM  '''||pathFile||''' ENCODING ''UTF-8'' DELIMITER ''|'';
';
  EXECUTE STATEMENT;
  RETURN 1;
EXCEPTION WHEN others THEN 
    RAISE EXCEPTION 'Error en la carga de datos de Cabecera: %', pathFile;
    RAISE EXCEPTION '% %', SQLERRM, SQLSTATE;
    RETURN 0;
END;
$$ LANGUAGE 'plpgsql';
--test
select LOAD_DATA_CAB('/home/webgeod/Documentos/CAB201502210001');

--## Data Detalle
CREATE TABLE importdbvalordetalle
(
  id serial NOT NULL,
  cod_mot character varying(255),
  cod_msj character varying(255),
  cod_usu character varying(255),
  des_dir character varying(255),
  des_mot character varying(255),
  des_sit character varying(255),
  fec_hoj character varying(255),
  hor_vis character varying(255),
  ind_dir character varying(255),
  ind_sit character varying(255),
  nom_msj character varying(255),
  nom_usu character varying(255),
  nro_hoj character varying(255),
  nro_man character varying(255),
  fin character varying(250)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE importdbvalordetalle
  OWNER TO nasqa;
--test
--SET client_encoding to LATIN1; 
--COPY importdbvalordetalle(cod_mot, cod_msj, cod_usu, des_dir, des_mot, 
--des_sit,fec_hoj,hor_vis,ind_dir,ind_sit,nom_msj,nom_usu,nro_hoj,nro_man,fin) 
--FROM '/home/webgeod/Develop/ws_eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/nasqa.values/dinamic/upload/2015-02-21_0434/DET20150128' 
--ENCODING 'LATIN1' DELIMITER '|' ;

CREATE OR REPLACE FUNCTION LOAD_DATA_DET(pathFile character varying) RETURNS INTEGER AS $$
DECLARE STATEMENT TEXT;
BEGIN
STATEMENT := '
DELETE FROM importdbvalordetalle WHERE 1=1;
SET client_encoding to ''UTF-8''; 
COPY importdbvalordetalle(cod_mot, cod_msj, cod_usu, des_dir, des_mot, 
des_sit,fec_hoj,hor_vis,ind_dir,ind_sit,nom_msj,nom_usu,nro_hoj,nro_man,fin) 
FROM  '''||pathFile||'''
ENCODING ''UTF-8'' DELIMITER ''|'' ;
';
  EXECUTE STATEMENT;
  RETURN 1;
EXCEPTION WHEN others THEN 
    RAISE EXCEPTION 'Error en la carga de datos de Detalle: %', pathFile;
    RAISE EXCEPTION '% %', SQLERRM, SQLSTATE;
    RETURN 0;
END;
$$ LANGUAGE 'plpgsql';
--test
select LOAD_DATA_DET('/home/webgeod/Documentos/DET201502210001');
