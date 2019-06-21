/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks;

import javax.persistence.EntityManager;

//import org.hibernate.StatelessSession;

/**
 * The main abstraction between different versions of Hibernate
 */
public interface HibernateVersionSupport {
	/**
	 * Generate a Hibernate SessionFactory and do any other prep work
	 */
	void setUp(Class[] annotatedClasses, String[] hbmfiles, boolean createSchema);

	/**
	 * Close the SessionFactory, etc
	 */
	void shutDown();

	EntityManager getEntityManager();

//	StatelessSession getStatelessSession();
}

