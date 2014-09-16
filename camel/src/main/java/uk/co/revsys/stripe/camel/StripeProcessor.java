package uk.co.revsys.stripe.camel;

import com.stripe.exception.StripeException;
import com.stripe.model.StripeObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class StripeProcessor implements Processor {

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            Object object = doProcess(exchange);
            if (object != null) {
                exchange.getOut().setBody(StripeObject.PRETTY_PRINT_GSON.toJson(object));
            }
        } catch (StripeException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public abstract StripeObject doProcess(Exchange exchng) throws Exception;

}
