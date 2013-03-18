package com.dimasco.jboss_in_action.bean;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.TransactionMode;
import org.jboss.arquillian.persistence.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dimasco.jboss_in_action.domain.Item;

/**
 * This simple test class demonstrates strange behavior of transactions in
 * Arquillian Persistence Extension.
 * 
 * @author dimas
 *
 */
@RunWith(Arquillian.class)
@Cleanup(phase = TestExecutionPhase.NONE)
public class ItemFacadeTest {

	@EJB
	private ItemFacadeLocal facade;
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(ItemFacadeLocal.class)
				.addClass(ItemFacade.class)
				.addClass(Item.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("persistence_test.xml", "persistence.xml");
	}
	
	/**
	 * Data from this method still in DB, but should be rolled back
	 */
	@Test
	@Transactional(TransactionMode.COMMIT)
	public void testAutomaticTransactionRollbackAfterException_modeCommit() {
		Item item = new Item();
		item.setName("Data with transaction mode = commit");
		
		facade.persistItem(item);
		
		throw new RuntimeException("Just to rollback transaction");
	}
	
	/**
	 * Data from this test method will be rolled back successfully.
	 */
	@Test
	@Transactional(TransactionMode.ROLLBACK)
	public void testAutomaticTransactionRollbackAfterException_modeRollback() {
		Item item = new Item();
		item.setName("Data with transaction mode = commit");
		
		facade.persistItem(item);
		
		throw new RuntimeException("Just to rollback transaction");
	}
}
