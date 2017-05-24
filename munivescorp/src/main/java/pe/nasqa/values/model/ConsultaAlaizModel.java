package pe.nasqa.values.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.ConsultaAlaizDao;
import pe.nasqa.values.dao.ReportesInternosDao;
//import pe.nasqa.values.dao.DistribucionDao;
//import pe.nasqa.values.dao.ImpExpDbDao;
import pe.nasqa.values.model.entity.Distribucion;
import pe.nasqa.values.model.entity.DistribucionCoord;
import pe.nasqa.values.model.entity.DistribucionVisita;
import pe.nasqa.values.model.entity.ExportVisita;
import pe.nasqa.values.model.entity.ReporteBBVAentr;
import pe.nasqa.values.model.entity.ReporteBBVApend;
import pe.nasqa.values.model.entity.ReporteBBVAworkf;
import pe.nasqa.values.model.entity.ReporteBCPtc;
import pe.nasqa.values.model.entity.ReporteCoordinacionDia;
import pe.nasqa.values.model.entity.ReporteEnvioProv;
import pe.nasqa.values.model.entity.ReporteGNB;
import pe.nasqa.values.model.entity.ReporteGNBtoken;
import pe.nasqa.values.model.entity.ReporteGNBvales;
import pe.nasqa.values.model.entity.ReporteHerbalife;
import pe.nasqa.values.model.entity.ReporteBCPdetalleGen;
import pe.nasqa.values.model.entity.ReporteRevistas;
import pe.nasqa.values.model.entity.ReporteOrbinGestion;


@Service
public class ConsultaAlaizModel{
	
	@Autowired
	ConsultaAlaizDao consultaAlaizsDao;

	
	private Logger log = Logger.getLogger(ConsultaAlaizModel.class);
	
	@Transactional
	public List<Object> getLista() {
		return consultaAlaizsDao.getLista();
	}
	
	

  
}
