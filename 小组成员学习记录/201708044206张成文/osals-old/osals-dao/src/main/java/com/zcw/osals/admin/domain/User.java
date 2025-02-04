package com.zcw.osals.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zcw.osals.base.domain.BaseEntity;

@Table(name = "T_ADMIN_USER")
@Entity
public class User extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1690250287281716094L;

	//////////////////////
	/*
	 * @Id private Long id;
	 */

	@Column(name = "NAME")
	String name;

	@Column(name = "KIND")
	String kind;

	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	Group group;

	@Column(name = "PRICE")
	String price;
	@Column(name = "WEIGHT")
	String weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	// public void setUsername(String string) {
	// // TODO Auto-generated method stub
	//
	// }

}
