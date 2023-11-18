package nl.jeroen.domain.persistence.doa;

import org.hibernate.Session;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import nl.jeroen.domain.persistence.interfaces.IGenericDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GenericDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	private Class<T> persistentClass;
	private Session session;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void setSession(Session s) {
		this.session = s;
	}

	protected Session getSession() {
		if (session == null) {
			throw new IllegalStateException("Session has not been set on DAO before usage");
		}
		return session;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public void load(T entity) {
		getSession().refresh(entity);
	}

	@Override
	public void save(T entity) {
		getSession().persist(entity);
	}

	@Override
	public void update(T entity) {
		getSession().merge(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().remove(entity);
	}

	@Override
	public void detach(T entity) {
		getSession().detach(entity);
	}

	@Override
	public T findById(ID id) {
		T entity = getSession().find(getPersistentClass(), id);
		return entity;

	}

	@Override
	public Set<T> findAll() {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		CriteriaQuery<T> criteria = builder.createQuery(getPersistentClass());
		Root<T> root = criteria.from(getPersistentClass());
		criteria.select(root);

		List<T> list = getSession().createQuery(criteria).getResultList();

		Set<T> set = new TreeSet<>();
		set.addAll(list);

		return set;
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

}