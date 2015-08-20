package asolution.db.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import asolution.db.exception.DBError;

public interface BaseDao<T extends Serializable> {

	public int insert(T model);

	public int insertForBatch(final List<T> models);

	public int update(T model);

	public int updateForBatch(final List<T> models);

	public int deleteById(final Serializable id);

	public int deleteByIdForBatch(final List<Serializable> list);

	public List<T> queryForAll();

	public T queryById(final Serializable id) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, DBError;

	public Serializable queryForPageTotal();

	public List<T> queryForPageList(final Serializable offset,
			final Serializable total);

}