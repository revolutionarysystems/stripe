package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class CreateCardProcessor extends StripeProcessor {

    private String customerId;
    private String cardToken;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Customer customer = Customer.retrieve(getCustomerId(), getApiKey());
        Map params = new HashMap();
        params.put("card", getCardToken());
        return customer.createCard(params, getApiKey());
    }

}
