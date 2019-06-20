/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Andrea Boriero
 */
@Entity
public class Entity7 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
