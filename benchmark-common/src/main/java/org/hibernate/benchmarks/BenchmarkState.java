/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;

//import org.hibernate.StatelessSession;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

/**
 * @author Andrea Boriero
 */
@State(Scope.Benchmark)
public class BenchmarkState {

	private HibernateVersionSupport versionSupport;

	@Setup
	public void setup() {
		try {
			versionSupport = VersionSupportFactory.buildHibernateVersionSupport();
			versionSupport.setUp( getAnnotatedClasses(), getHbmFiles(), createSchema() );

			populateDatabase();
		}
		catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

	protected void populateDatabase() {
	}

	protected void cleanUpDatabase() {

	}

	public <T> T inTransaction(Function<EntityManager, T> function) {
		EntityManager em = versionSupport.getEntityManager();
		T result = null;
		try {
			em.getTransaction().begin();
			result = function.apply( em );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			if ( em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
		return result;
	}


//	public <T> T inStatelessTransaction(Function<StatelessSession, T> function) {
//		StatelessSession statelessSession = versionSupport.getStatelessSession();
//		T result = null;
//		try {
//			statelessSession.getTransaction().begin();
//			result = function.apply( statelessSession );
//			statelessSession.getTransaction().commit();
//		}
//		catch (Exception e) {
//			if ( statelessSession.getTransaction().isActive() ) {
//				statelessSession.getTransaction().rollback();
//			}
//			throw e;
//		}
//		finally {
//			statelessSession.close();
//		}
//		return result;
//	}

	public void inTransaction(Consumer<EntityManager> consumer) {
		EntityManager em = versionSupport.getEntityManager();
		try {
			em.getTransaction().begin();
			consumer.accept( em );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			if ( em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
		}
		finally {
			em.close();
		}
	}

//	public StatelessSession getStatelessSession() {
//		return versionSupport.getStatelessSession();
//	}

	protected Class[] getAnnotatedClasses() {
		return new Class[] {};
	}

	protected String[] getHbmFiles() {
		return new String[] {};
	}

	protected boolean createSchema() {
		return false;
	}

	@TearDown
	public void shutdown() {
		try {
			cleanUpDatabase();
			versionSupport.shutDown();
		}
		catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
	}

}
