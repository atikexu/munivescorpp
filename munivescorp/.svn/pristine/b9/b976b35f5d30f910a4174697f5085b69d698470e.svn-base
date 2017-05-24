package pe.nasqa.values.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.LogCargaDataDao;
import pe.nasqa.values.model.entity.ClienteAgencia;
import pe.nasqa.values.model.entity.LogCargaData;
import pe.nasqa.values.model.entity.Rendicion;

@Service
public class LogCargaDataModel{
	
	@Autowired
	private LogCargaDataDao LogCargaDataDao;


		
	
	
	
	
	@Transactional
	
	public void inserLogCargaData(String nombre,String fecha,String usuario)
	 {
		LogCargaDataDao.inserLogCargaData(nombre,fecha,usuario);
	}
	
	@Transactional
	 public boolean countLogCargaDataNombre(String nombre)
	 {
		return  LogCargaDataDao.countLogCargaDataNombre(nombre);
	 }
}
