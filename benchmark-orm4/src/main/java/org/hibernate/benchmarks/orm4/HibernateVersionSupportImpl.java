/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks.orm4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.benchmarks.HibernateVersionSupport;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.MetadataSources;

import org.jboss.logging.Logger;

/**
 * @author Steve Ebersole
 */
public class HibernateVersionSupportImpl implements HibernateVersionSupport {
	private static final Logger log = Logger.getLogger( HibernateVersionSupportImpl.class );

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

		Class klass = SessionFactory.class;
		String location = klass.getResource( '/' + klass.getName().replace( '.', '/') + ".class").getFile();
		log.info( ">>> Using hibernate from jar = " + location.substring( 0,location.indexOf( '!' ) ));
//		log.info( "Allow enhancement proxy = " + sessionFactory.getProperties().get( AvailableSettings.ALLOW_ENHANCEMENT_AS_PROXY ));
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
		return ( (EntityManagerFactory) sessionFactory ).createEntityManager();
	}

	public StatelessSession getStatelessSession() {
		return sessionFactory.openStatelessSession();
	}
}
