package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class CreateCustomerProcessor extends StripeProcessor {

    private String cardToken;
    private String description;

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("card", getCardToken());
        params.put("description", getDescription());
        System.out.println("apiKey = " + getApiKey());
        Customer customer = Customer.create(params, getApiKey());
        return customer;
    }

}
