package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING, length = 1)
//@SecondaryTable(name="USER_PICTURES", pkJoinColumns=@PrimaryKeyJoinColumn(name="USER_ID")) 
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String firstName;
	private String lastName;
	// private byte[] picture;
	private Date birthDate;
	private ContactInfo contactInfo;
	private BillingInfo billingInfo;
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
	 * @Basic(fetch=FetchType.LAZY) public byte[] getPicture() { return picture; }
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
	@PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "CONTACT_USER_ID")
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_BILLING_ID", referencedColumnName = "BILLING_ID")
	public BillingInfo getBillingInfo() {
		return billingInfo;
	}

	public void setBillingInfo(BillingInfo billingInfo) {
		this.billingInfo = billingInfo;
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