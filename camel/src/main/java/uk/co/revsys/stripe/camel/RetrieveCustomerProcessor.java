package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import org.apache.camel.Exchange;

public class RetrieveCustomerProcessor extends StripeProcessor{

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        return Customer.retrieve(getId(), getApiKey());
    }

}
