package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.Plan;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class ChangePlanProcessor extends StripeProcessor{

    private String customerId;
    private String planId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
    
    @Override
    public StripeObject doProcess(Exchange exchange) throws Exception {
        Plan plan = Plan.retrieve(planId, getApiKey());
        Customer customer = Customer.retrieve(customerId, getApiKey());
        Subscription subscription = customer.getSubscriptions().getData().get(0);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("plan", planId);
        subscription.update(params, getApiKey());
        return subscription;
    }

}
