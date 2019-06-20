/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * @author Andrea Boriero
 */
@Entity
public class TestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity5 entity5;

	@OneToMany
	List<Entity1> entity1s;

	@OneToMany
	List<Entity2> entity2s;

	@OneToMany
	List<Entity3> entity3s;

	@OneToMany
	List<Entity4> entity4s;

	@OneToMany
	List<Entity5> entity5s;

	@OneToMany
	List<Entity6> entity6s;

	@OneToMany
	List<Entity7> entity7s;

	@OneToMany
	List<Entity8> entity8s;


	String name;

	public TestEntity() {
	}

	TestEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entity5 getEntity5() {
		return entity5;
	}

	public void setEntity5(Entity5 entity5) {
		this.entity5 = entity5;
	}

	public Long getId() {
		return id;
	}
}
