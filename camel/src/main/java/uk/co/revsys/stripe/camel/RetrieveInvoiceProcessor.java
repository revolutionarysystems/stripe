package uk.co.revsys.stripe.camel;

import com.stripe.model.Invoice;
import com.stripe.model.StripeObject;
import org.apache.camel.Exchange;

public class RetrieveInvoiceProcessor extends StripeProcessor{

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        return Invoice.retrieve(getId(), getApiKey());
    }

}
