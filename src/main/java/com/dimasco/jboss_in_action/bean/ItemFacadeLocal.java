package com.dimasco.jboss_in_action.bean;

import java.util.List;

import javax.ejb.Local;

import com.dimasco.jboss_in_action.domain.Item;


@Local
public interface ItemFacadeLocal {

	void testPersistWithException();
	
	void persistItem(Item item);
	
	void testUpdateWithoutTransaction(Item item);
	
	List<Item> testListAllWithoutTransaction();
	
	Item testGetByIdWithoutTransaction(Long id);
}
