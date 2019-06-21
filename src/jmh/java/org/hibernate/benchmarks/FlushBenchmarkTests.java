/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.benchmarks;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.benchmarks.model.Entity1;
import org.hibernate.benchmarks.model.Entity2;
import org.hibernate.benchmarks.model.Entity3;
import org.hibernate.benchmarks.model.Entity4;
import org.hibernate.benchmarks.model.Entity5;
import org.hibernate.benchmarks.model.Entity6;
import org.hibernate.benchmarks.model.Entity7;
import org.hibernate.benchmarks.model.Entity8;
import org.hibernate.benchmarks.model.TestEntity;
//import org.hibernate.bytecode.enhance.spi.interceptor.LazyAttributeLoadingInterceptor;
//import org.hibernate.engine.spi.PersistentAttributeInterceptable;
//import org.hibernate.engine.spi.PersistentAttributeInterceptor;

import org.jboss.logging.Logger;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

/**
 * @author Andrea Boriero
 */
public class FlushBenchmarkTests extends BenchmarkState {
	private static final Logger log = Logger.getLogger( FlushBenchmarkTests.class );


	public Long lastChildId;

	public static final int NUMBER_OF_ENTITIES_TO_Create = 500;


	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public Object testUpdatingAField(FlushBenchmarkTests state) {

		return state.inTransaction(
				entityManager -> {
					TestEntity loadedTestEntity = entityManager.getReference( TestEntity.class, lastChildId );

//					final PersistentAttributeInterceptable interceptable = (PersistentAttributeInterceptable) loadedTestEntity;
//					final PersistentAttributeInterceptor interceptor = interceptable.$$_hibernate_getInterceptor();
//					if ( interceptor instanceof LazyAttributeLoadingInterceptor ) {
//						throw new RuntimeException( "It should be an instance of EnhancementAsProxyLazinessInterceptor" );

//					}
					loadedTestEntity.setName( "new name" );
					return lastChildId;
				}
		);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public Object testGetResultList(FlushBenchmarkTests state) {

		return state.inTransaction(
				entityManager -> {
					List<TestEntity> testEntitys = entityManager.createQuery( "from TestEntity" ).getResultList();
					for ( TestEntity loadedTestEntity : testEntitys ) {
						loadedTestEntity.setName( "new name" );
					}
					return testEntitys.size();
				}
		);
	}

//	public static class EndToEndBenchmarkState extends BenchmarkState {

	@Override
	protected boolean createSchema() {
		return true;
	}

	@Override
	protected void populateDatabase() {
		inTransaction(
				entityManager -> {
					Entity5 entity5 = new Entity5();
					entityManager.persist( entity5 );
					TestEntity testEntity = null;
					for ( int i = 0; i < NUMBER_OF_ENTITIES_TO_Create + 1; i++ ) {
						testEntity = new TestEntity();
						// Association management should kick in here
						testEntity.setEntity5( entity5 );
						entity5.addTestEntity( testEntity );

						entityManager.persist( testEntity );
					}
					lastChildId = testEntity.getId();
				} );
	}

	@Override
	protected void cleanUpDatabase() {
		inTransaction(
				entityManager -> {
					entityManager.createQuery( "delete from TestEntity" ).executeUpdate();
					entityManager.createQuery( "delete from Entity5" ).executeUpdate();
				}
		);
	}

	@Override
	protected Class[] getAnnotatedClasses() {
		return new Class[] {
				TestEntity.class,
				Entity1.class,
				Entity2.class,
				Entity3.class,
				Entity4.class,
				Entity5.class,
				Entity6.class,
				Entity7.class,
				Entity8.class
		};
	}
//	}
}
