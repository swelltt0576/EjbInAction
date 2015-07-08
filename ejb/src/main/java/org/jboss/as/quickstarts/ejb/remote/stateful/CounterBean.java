package org.jboss.as.quickstarts.ejb.remote.stateful;

import javax.ejb.Stateful;

/**
 * @author Jaikiran Pai
 */
@Stateful
public class CounterBean implements RemoteCounter {

	private int count = 0;

	@Override
	public void increment() {
		this.count++;
	}

	@Override
	public void decrement() {
		this.count--;
	}

	@Override
	public int getCount() {
		return this.count;
	}
}