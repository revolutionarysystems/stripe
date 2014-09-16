package uk.co.revsys.stripe.camel;

import com.stripe.model.Invoice;
import com.stripe.model.StripeObject;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;

public class RetrieveInvoicesProcessor extends StripeProcessor {

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Map params = new HashMap();
        params.put("customer", getCustomerId());
        return Invoice.all(params, getApiKey());
    }

}
