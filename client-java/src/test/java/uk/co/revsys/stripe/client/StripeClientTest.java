/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.co.revsys.stripe.client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class StripeClientTest {
    
    public StripeClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of plans method, of class StripeClient.
     */
    @org.junit.Test
    public void testPlans() throws Exception {
        System.out.println("plans");
        StripeClient instance = new StripeClient();
        String expResult = "\"url\": \"/v1/plans\"";
        String result = instance.plans();
        assertTrue(result.contains(expResult));
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
