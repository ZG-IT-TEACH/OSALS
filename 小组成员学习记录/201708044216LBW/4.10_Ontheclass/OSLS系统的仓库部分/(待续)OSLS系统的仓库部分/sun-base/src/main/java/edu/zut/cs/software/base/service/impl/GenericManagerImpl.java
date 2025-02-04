package edu.zut.cs.software.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import edu.zut.cs.software.base.dao.GenericDao;
import edu.zut.cs.software.base.domain.BaseEntity;
import edu.zut.cs.software.base.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 *
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
@Transactional
public class GenericManagerImpl<T extends BaseEntity, PK extends Serializable> implements GenericManager<T, PK> {
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = LogManager.getLogger(GenericManagerImpl.class.getName());

	protected GenericDao<T, PK> dao;

	public void delete(PK id) {      //pk可以是任意类型
		this.dao.deleteById(id);       //以后删除推荐可以只是修改一下保存时间，并没有真正删除
	}

	public List<T> findAll() {
		return this.dao.findAll();
	}

	public Page<T> findAll(Pageable page) {
		Page<T> result = this.dao.findAll(page);
		return result;
	}

	public T findById(PK id)
	{
		return this.dao.getOne(id);
	}

	public List<T> save(Iterable<T> entities)
	{
		return this.dao.saveAll(entities);
	}

	public T save(T entity) {
		return this.dao.saveAndFlush(entity);
	}

}
