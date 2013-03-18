package com.dimasco.jboss_in_action.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author DIMAS
 *
 */
@Entity
@Table(name="ITEM")
public class Item implements Serializable {
		
	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	private String name;
	
	public Item() {
		
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}
	
}
