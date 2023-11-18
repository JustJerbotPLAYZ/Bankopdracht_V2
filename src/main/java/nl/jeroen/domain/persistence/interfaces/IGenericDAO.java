package nl.jeroen.domain.persistence.interfaces;

import java.io.Serializable;
import java.util.Set;

public interface IGenericDAO<T, ID extends Serializable> {
	
	public void load(T entity);

	public void save(T entity);

	public void update(T entity);

	public void delete(T entity);

	public void detach(T entity);

	public T findById(ID id);

	public Set<T> findAll();

}