package com.ejb3inaction.actionbazaar.buslogic;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.ejb3inaction.actionbazaar.persistence.Category;

@Remote
public interface BazaarAdmin {
	public Category findByFullCategoryName(String categoryName);

	public List findByCategoryName(String categoryName);

	public List getItemByDate(Date currentDate);

	public List getItemByPriceRange(Double lowPrice, Double highPrice);

	public List getUserWithItems();

	public List getUserWithNoItems();

	public List getUserWithItemsWithNativeQuery();

	public List getUserWithItemsWithNamedNativeQuery();

	public List findByUser(String userId);
}