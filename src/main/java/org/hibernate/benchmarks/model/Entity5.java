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
import javax.persistence.OneToMany;

/**
 * @author Andrea Boriero
 */
@Entity
public class Entity5 {
	String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@OneToMany(mappedBy = "entity5", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<TestEntity> testEntities;

	void setTestEntities(List<TestEntity> testEntities) {
		this.testEntities = testEntities;
	}

	public void addTestEntity(TestEntity testEntity){
		if(this.testEntities == null){
			this.testEntities = new ArrayList<>(  );
		}
		this.testEntities.add( testEntity );
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
