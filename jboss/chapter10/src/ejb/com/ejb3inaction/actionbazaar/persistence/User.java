package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( {
		@NamedQuery(name = "findUserWithItems", query = "SELECT distinct u FROM User u WHERE exists (SELECT i FROM Item i WHERE i.user=u)"),
		@NamedQuery(name = "findUserWithNoItems", query = "SELECT distinct u FROM User u WHERE  u.items is EMPTY") })
@NamedNativeQuery(name = "findUserWithMoreItems", query = "select user_id, first_name, last_name, birth_date from users where user_id in (select seller_id from items group by seller_id having count(*) >?1)", resultClass = User.class)
@Table(name = "USERS")
// @SecondaryTable(name="USER_PICTURES",
// pkJoinColumns=@PrimaryKeyJoinColumn(name="USER_ID"))
// @Inheritance(strategy=InheritanceType.JOINED)
// @DiscriminatorColumn(name="USER_TYPE",discriminatorType=CHAR, length=1)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String firstName;
	private String lastName;
	// private byte[] picture;
	private Date birthDate;
	private BillingInfo billingInfo;
	private Set<Item> items;
	private Set<Category> categories;

	public User() {
	}

	public User(String userId, String firstName, String lastName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	@Column(name = "USER_ID", nullable = false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "FIRST_NAME", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.toUpperCase();
	}

	@Column(name = "LAST_NAME", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * @Column(name="PICTURE", table="USER_PICTURES") @Lob
	 * @Basic(fetch=FetchType.EAGER) public byte[] getPicture() { return
	 * picture; }
	 * 
	 * public void setPicture(byte[] picture) { this.picture = picture; }
	 */
	@Column(name = "BIRTH_DATE")
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BILLING_ID", referencedColumnName = "BILLING_ID")
	public BillingInfo getBillingInfo() {
		return billingInfo;
	}

	public void setBillingInfo(BillingInfo billingInfo) {
		this.billingInfo = billingInfo;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setUser(this);
		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setUser(null);
		return item;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setUser(this);
		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setUser(null);
		return category;
	}
}