package com.ejb3inaction.actionbazaar.persistence;

import java.io.Serializable;
import java.util.Date;

public class CategoryPK implements Serializable {
	private static final long serialVersionUID = 1L;
	String categoryName;
	Date createDate;

	public boolean equals(Object other) {
		if (other instanceof CategoryPK) {
			final CategoryPK otherCategoryPK = (CategoryPK) other;
			boolean areEqual = (otherCategoryPK.equals(categoryName) && otherCategoryPK.createDate
					.equals(createDate));

			return areEqual;
		}

		return false;
	}

	public int hashCode() {
		// Add custom hashCode() implementation here
		return super.hashCode();
	}
}