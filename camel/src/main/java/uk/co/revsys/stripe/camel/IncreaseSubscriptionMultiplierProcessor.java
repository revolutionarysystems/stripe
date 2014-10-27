package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class IncreaseSubscriptionMultiplierProcessor extends StripeProcessor{

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Customer customer = Customer.retrieve(customerId, getApiKey());
        Subscription subscription = customer.getSubscriptions().getData().get(0);
        int quantity = subscription.getQuantity();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("quantity", quantity+1);
        subscription.update(params, getApiKey());
        return subscription;
    }

}
