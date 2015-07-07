package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners( { ItemVerifier.class })
@NamedQueries( {
		@NamedQuery(name = "findItemByDate", query = "SELECT i FROM Item AS i WHERE i.bidEndDate > :currentDate"),
		@NamedQuery(name = "findItemByInitialPrice", query = "SELECT i FROM Item AS i WHERE i.initialPrice between :lowPrice and :highPrice") })
@Table(name = "ITEMS")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long itemId;
	private String itemName;
	private Set<Category> categories;
	private Double initialPrice;
	private Date bidStartDate;
	private Date bidEndDate;
	private User user;
	private Date createdDate;

	public Item() {
	}

	public Item(Long itemId) {
		this.itemId = itemId;
	}

	@TableGenerator(name = "ITEM_TABLE_GENERATOR", table = "TABLE_SEQUENCE_GEN", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ITEM_SEQ")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEM_ID", nullable = false)
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "ITEM_NAME")
	// @Basic(fetch=FetchType.LAZY)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@ManyToMany(mappedBy = "items")
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		return category;
	}

	@Column(name = "INITIAL_PRICE")
	public Double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}

	@Column(name = "BID_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBidStartDate() {
		return bidStartDate;
	}

	public void setBidStartDate(Date bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	@Column(name = "BID_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "SELLER_ID", referencedColumnName = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}