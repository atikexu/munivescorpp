package pe.nasqa.values.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.CoordinacionFeriadoDao;
import pe.nasqa.values.model.entity.RegistroFeriado;

@Service
public class CoordinacionFeriadoModel {
	
	@Autowired
	private CoordinacionFeriadoDao coordFeriadoDao;
	
	private Logger log = Logger.getLogger(CoordinacionFeriadoModel.class);
	
	@Transactional(readOnly=true)
	public List<RegistroFeriado> listarFeriados(){
		return coordFeriadoDao.findAll();
	}
}
