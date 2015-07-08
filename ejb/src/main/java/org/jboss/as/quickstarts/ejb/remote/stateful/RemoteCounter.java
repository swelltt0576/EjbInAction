package org.jboss.as.quickstarts.ejb.remote.stateful;

import javax.ejb.Remote;
 
/**
 * @author Jaikiran Pai
 */
@Remote
public interface RemoteCounter {
 
    void increment();
 
    void decrement();
 
    int getCount();
}