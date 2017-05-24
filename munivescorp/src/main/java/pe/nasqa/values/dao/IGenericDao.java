package pe.nasqa.values.dao;

import java.util.List;

public interface IGenericDao<T,K> {

	T findOne(K id);

	List<T> findAll();

	void create(T entity);

	void update(T entity);

	void delete(T entity);

	void deleteById(K entityId);
	
}
