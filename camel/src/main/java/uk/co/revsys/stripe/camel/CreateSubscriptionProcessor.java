package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class CreateSubscriptionProcessor extends StripeProcessor {

    private String customerId;
    private String plan;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Customer customer = Customer.retrieve(getCustomerId(), getApiKey());
        Map params = new HashMap();
        params.put("plan", getPlan());
        Subscription subscription = customer.createSubscription(params, getApiKey());
        return subscription;
    }

}
