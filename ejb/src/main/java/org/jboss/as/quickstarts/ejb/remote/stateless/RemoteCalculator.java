package org.jboss.as.quickstarts.ejb.remote.stateless;

import javax.ejb.Remote;
 
/**
 * @author Jaikiran Pai
 */
@Remote
public interface RemoteCalculator {
 
    int add(int a, int b);
 
    int subtract(int a, int b);
}