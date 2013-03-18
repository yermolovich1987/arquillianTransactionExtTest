package com.dimasco.jboss_in_action.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dimasco.jboss_in_action.domain.Item;

@Stateless(name = "testCBOperationsFacade")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ItemFacade implements ItemFacadeLocal {
	
	@PersistenceContext(unitName="testDatabase")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void testPersistWithException() {
		Item item = new Item();
		item.setName("test item");
		
		em.persist(item);
		
		Query query = em.createQuery("select item from Item item where name=:name");
		
		query.setParameter("name", "test item");
		query.getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void persistItem(Item item) {
		em.persist(item);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void testUpdateWithoutTransaction(Item item) {
		item.setName("New name");
		item = em.merge(item);
		item.setName("Changed name");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> testListAllWithoutTransaction() {
		String queryText = "select item from Item item";

		return em.createQuery(queryText).getResultList();
	}

	@Override
	public Item testGetByIdWithoutTransaction(Long id) {
		return em.find(Item.class, id);
	}

}
