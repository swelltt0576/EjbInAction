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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners( { CategoryNotifier.class })
@Table(name = "CATEGORIES")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long categoryId;
	private String categoryName;
	private Category parentCategory;
	private Set<Category> subcategories;
	private Set<Item> items;
	private User user;
	private Date createDate;

	public Category() {
	}

	public Category(Long categoryId) {
		this.categoryId = categoryId;
	}

	@SequenceGenerator(name = "CATEGORY_SEQUENCE_GENERATOR", sequenceName = "CATEGORY_SEQUENCE", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQUENCE_GENERATOR")
	@Column(name = "CATEGORY_ID", nullable = false)
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "CATEGORY_NAME")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "CATEGORY_ID")
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@OneToMany(mappedBy = "parentCategory")
	public Set<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Set<Category> subcategories) {
		this.subcategories = subcategories;
	}

	public Category addSubcategory(Category subcategory) {
		getSubcategories().add(subcategory);
		return subcategory;
	}

	public Category removeSubcategory(Category subcategory) {
		getSubcategories().remove(subcategory);
		subcategory.setParentCategory(null);
		return subcategory;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "CATEGORIES_ITEMS", joinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID"))
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.addCategory(this);
		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setCategories(null);
		return item;
	}

	@ManyToOne
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CREATE_DATE", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}