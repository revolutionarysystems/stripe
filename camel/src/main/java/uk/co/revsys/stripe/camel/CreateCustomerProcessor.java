package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class CreateCustomerProcessor extends StripeProcessor {

    private String cardToken;
    private String description;
    private String plan;

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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        if (getCardToken() != null && !getCardToken().isEmpty()) {
            params.put("card", getCardToken());
        }
        params.put("description", getDescription());
        if (getPlan() != null && !getPlan().isEmpty()) {
            params.put("plan", getPlan());
        }
        System.out.println("apiKey = " + getApiKey());
        Customer customer = Customer.create(params, getApiKey());
        return customer;
    }

}
