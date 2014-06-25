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
    public void setUp() throws Exception{
        System.out.println("plans");
        StripeClient instance = new StripeClient("sk_test_4qo3vbZaV6CI9l3ITyox2St3");
        String result = instance.plans();
        if (!result.contains("\"utp\"")){
            System.out.println("plan create");
            String jsonInput = "{\"amount\":\"2500\",\"interval\":\"month\",\"name\":\"Unit Test Plan\",\"currency\":\"gbp\",\"id\":\"utp\"}";
            result = instance.planCreate(jsonInput);
            System.out.println(result);
        }
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
        StripeClient instance = new StripeClient("sk_test_4qo3vbZaV6CI9l3ITyox2St3");
        String expResult = "\"url\": \"/v1/plans\"";
        String result = instance.plans();
        assertTrue(result.contains(expResult));
    }
    
    /**
     * Test of getCardToken method, of class StripeClient.
     */
    @org.junit.Test
    public void testCardToken() throws Exception {
        System.out.println("card Token");
        StripeClient instance = new StripeClient("sk_test_4qo3vbZaV6CI9l3ITyox2St3");
        String expResult = "\"used\": false";
        String jsonInput = "{\"card\": {\"number\":\"4242424242424242\",\"exp_month\":\"12\",\"exp_year\":\"2015\",\"cvc\":\"123\"}}";
        String result = instance.getCardToken(jsonInput);
        System.out.println(result);
        String token = result.substring(result.indexOf("\"id\":")+7,result.indexOf("\"id\":")+35);
        System.out.println(token);
        assertTrue(result.contains(expResult));
    }

    @org.junit.Test
    public void testCustomerCreate() throws Exception {
        System.out.println("customer create");
        StripeClient instance = new StripeClient("sk_test_4qo3vbZaV6CI9l3ITyox2St3");
        String expResult = "\"livemode\": false";
        String jsonInput = "{\"card\": {\"number\":\"4242424242424242\",\"exp_month\":\"12\",\"exp_year\":\"2015\",\"cvc\":\"123\"}}";
        String result = instance.getCardToken(jsonInput);
        String token = result.substring(result.indexOf("\"id\":")+7,result.indexOf("\"id\":")+35);
        System.out.println(token);
        
        jsonInput = "{\"card\": \""+token+"\",\"description\":\"Test Customer\"}";
        result = instance.customerCreate(jsonInput);
        System.out.println(result);
        assertTrue(result.contains(expResult));
    }

    @org.junit.Test
    public void testSubscriptionCreate() throws Exception {
        System.out.println("subscription create");
        StripeClient instance = new StripeClient("sk_test_4qo3vbZaV6CI9l3ITyox2St3");
        String expResult = "\"livemode\": false";
        String jsonInput = "{\"card\": {\"number\":\"4242424242424242\",\"exp_month\":\"12\",\"exp_year\":\"2015\",\"cvc\":\"123\"}}";
        String result = instance.getCardToken(jsonInput);
        String token = result.substring(result.indexOf("\"id\":")+7,result.indexOf("\"id\":")+35);
        System.out.println(token);
        jsonInput = "{\"card\": \""+token+"\",\"description\":\"Test Customer\"}";
        result = instance.customerCreate(jsonInput);
        String custId = result.substring(result.indexOf("\"id\":")+7,result.indexOf("\"id\":")+25);
        jsonInput = "{\"customerId\": \""+custId+"\",\"plan\":\"utp\"}";
        System.out.println(jsonInput);
        result = instance.subscriptionCreate(jsonInput);
        System.out.println(result);
        assertTrue(result.contains(expResult));
    }



}
