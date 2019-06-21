/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks.model;

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
	Entity1 entity1;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity2 entity2;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity3 entity3;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity4 entity4;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity5 entity5;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity6 entity6;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity7 entity7;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	Entity8 entity8;

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

	public Entity1 getEntity1() {
		return entity1;
	}

	public void setEntity1(Entity1 entity1) {
		this.entity1 = entity1;
	}

	public Entity2 getEntity2() {
		return entity2;
	}

	public void setEntity2(Entity2 entity2) {
		this.entity2 = entity2;
	}

	public Entity3 getEntity3() {
		return entity3;
	}

	public void setEntity3(Entity3 entity3) {
		this.entity3 = entity3;
	}

	public Entity4 getEntity4() {
		return entity4;
	}

	public void setEntity4(Entity4 entity4) {
		this.entity4 = entity4;
	}

	public Entity6 getEntity6() {
		return entity6;
	}

	public void setEntity6(Entity6 entity6) {
		this.entity6 = entity6;
	}

	public Entity7 getEntity7() {
		return entity7;
	}

	public void setEntity7(Entity7 entity7) {
		this.entity7 = entity7;
	}

	public Entity8 getEntity8() {
		return entity8;
	}

	public void setEntity8(Entity8 entity8) {
		this.entity8 = entity8;
	}

	public List<Entity1> getEntity1s() {
		return entity1s;
	}

	public void setEntity1s(List<Entity1> entity1s) {
		this.entity1s = entity1s;
	}

	public List<Entity2> getEntity2s() {
		return entity2s;
	}

	public void setEntity2s(List<Entity2> entity2s) {
		this.entity2s = entity2s;
	}

	public List<Entity3> getEntity3s() {
		return entity3s;
	}

	public void setEntity3s(List<Entity3> entity3s) {
		this.entity3s = entity3s;
	}

	public List<Entity4> getEntity4s() {
		return entity4s;
	}

	public void setEntity4s(List<Entity4> entity4s) {
		this.entity4s = entity4s;
	}

	public List<Entity5> getEntity5s() {
		return entity5s;
	}

	public void setEntity5s(List<Entity5> entity5s) {
		this.entity5s = entity5s;
	}

	public List<Entity6> getEntity6s() {
		return entity6s;
	}

	public void setEntity6s(List<Entity6> entity6s) {
		this.entity6s = entity6s;
	}

	public List<Entity7> getEntity7s() {
		return entity7s;
	}

	public void setEntity7s(List<Entity7> entity7s) {
		this.entity7s = entity7s;
	}

	public List<Entity8> getEntity8s() {
		return entity8s;
	}

	public void setEntity8s(List<Entity8> entity8s) {
		this.entity8s = entity8s;
	}
}
