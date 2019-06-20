/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks.orm5;

import javax.persistence.EntityManager;

import org.hibernate.StatelessSession;
import org.hibernate.benchmarks.HibernateVersionSupport;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.spi.SessionFactoryImplementor;

/**
 * @author Steve Ebersole
 */
public class HibernateVersionSupportImpl implements HibernateVersionSupport {
	private StandardServiceRegistry serviceRegistry;
	private SessionFactoryImplementor sessionFactory;

	@Override
	public void setUp(Class[] annotatedClasses, String[] hbmfiles, boolean createSchema) {
		StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
		if ( createSchema ) {
			standardServiceRegistryBuilder.applySetting(
					org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO,
					"create-drop"
			);
		}
		serviceRegistry = standardServiceRegistryBuilder.build();
		MetadataSources metadataSources = new MetadataSources( serviceRegistry );
		for ( int i = 0; i < hbmfiles.length; i++ ) {
			metadataSources.addResource( hbmfiles[i] );
		}
		for ( int i = 0; i < annotatedClasses.length; i++ ) {
			metadataSources.addAnnotatedClass( annotatedClasses[i] );
		}
		sessionFactory = (SessionFactoryImplementor) metadataSources
				.buildMetadata()
				.buildSessionFactory();
		System.out.println( ">>>> Allow enhancement proxy = " + sessionFactory.getProperties().get( AvailableSettings.ALLOW_ENHANCEMENT_AS_PROXY ));
	}

	@Override
	public void shutDown() {
		if ( sessionFactory != null ) {
			try {
				sessionFactory.close();
			}
			catch (Exception ignore) {
			}
		}

		if ( serviceRegistry != null ) {
			try {
				StandardServiceRegistryBuilder.destroy( serviceRegistry );
			}
			catch (Exception ignore) {
			}
		}
	}

	@Override
	public EntityManager getEntityManager() {
		return sessionFactory.openSession();
	}

	public StatelessSession getStatelessSession() {
		return sessionFactory.openStatelessSession();
	}
}
