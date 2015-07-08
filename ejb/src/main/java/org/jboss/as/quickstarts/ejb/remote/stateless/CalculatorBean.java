package org.jboss.as.quickstarts.ejb.remote.stateless;
 
import javax.ejb.Stateless;
 
/**
 * @author Jaikiran Pai
 */
@Stateless
public class CalculatorBean implements RemoteCalculator {
 
    @Override
    public int add(int a, int b) {
        return a + b;
    }
 
    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}