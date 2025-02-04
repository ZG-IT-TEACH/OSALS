package com.zcw.osals.admin.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zcw.osals.admin.domain.Group;
import com.zcw.osals.base.dao.GenericTreeDaoTestCase;

public class GroupDaoTest extends GenericTreeDaoTestCase<Long, Group, GroupDao> {

	GroupDao groupDao;

	// @Autowired自动装配方式，从IoC容器中去查找到，并返回给该属性
	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
		this.dao = this.groupDao;
	}

	@Test
	public void testGetRoot() {
		int root_size = 10;
		for (int i = 0; i < root_size; i++) {
			Group group = new Group();
			// setName???? ----JpaRepository
			group.setName("group_" + i);
			for (int j = 0; j < 10; j++) {
				Group g = new Group();
				g.setName("group_" + i + "_" + j);
				// setParent??? ----JpaRepository
				g.setParent(group);
			}
			this.groupDao.save(group);
		}

		List<Group> roots = this.groupDao.getRoot();
		assertEquals(root_size, roots.size());

		if (logger.isInfoEnabled()) {
			logger.info("testGetRoot() - List<Group> roots=" + roots); //$NON-NLS-1$
		}
	}

}
