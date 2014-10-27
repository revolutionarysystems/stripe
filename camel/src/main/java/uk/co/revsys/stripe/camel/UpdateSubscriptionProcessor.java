package uk.co.revsys.stripe.camel;

import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class UpdateSubscriptionProcessor extends StripeProcessor{

    private String customerId;
    private Integer quantity;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Customer customer = Customer.retrieve(customerId, getApiKey());
        Subscription subscription = customer.getSubscriptions().getData().get(0);
        Map<String, Object> params = new HashMap<String, Object>();
        if(quantity != null){
            params.put("quantity", quantity);
        }
        subscription.update(params, getApiKey());
        return subscription;
    }

}
