package edu.zut.cs.software.star.admin.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.zut.cs.software.base.dao.GenericDaoTestCase;
import edu.zut.cs.software.star.admin.domain.Role;


/**
 * 
 * @author 研哥哥
 *
 */

public class RoleDaoTest extends GenericDaoTestCase<Long, Role, RoleDao> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(RoleDaoTest.class.getName());
	
	@Autowired
	RoleDao roleDao;

	@Test
	public void test() {
		Role r = new Role();
		r.setName("test_role");

		Role test_role = this.roleDao.save(r);
		if (logger.isInfoEnabled()) {
			logger.info("test() - Role test_role={}", test_role); //$NON-NLS-1$
		}
		
		Long role_id = test_role.getId();
		Role result = this.roleDao.getOne(role_id);
		if (logger.isInfoEnabled()) {
			logger.info("test() - Role result={}", result); //$NON-NLS-1$
		}

		assertEquals(test_role, result);
		
		this.roleDao.getOne(role_id).setName("test_role_update");;
		List<Role> all_update = this.roleDao.findAll();
		if (logger.isInfoEnabled()) {
			logger.info("test() - List<Role> all_update={}", all_update); //$NON-NLS-1$
		}

		this.roleDao.deleteById(role_id);
		List<Role> all_delete = this.roleDao.findAll();
		if (logger.isInfoEnabled()) {
			logger.info("test() - List<Role> all_delete={}", all_delete); //$NON-NLS-1$
		}
		

	}
	
}
