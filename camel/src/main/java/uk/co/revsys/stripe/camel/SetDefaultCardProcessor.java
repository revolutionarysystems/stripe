package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import org.apache.camel.Exchange;

public class SetDefaultCardProcessor extends StripeProcessor{

    private String customerId;
    private String cardId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Customer customer = Customer.retrieve(getCustomerId(), getApiKey());
        customer.setDefaultCard(getCardId());
        return customer;
    }

}
