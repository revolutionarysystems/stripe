package uk.co.revsys.stripe.camel;

import java.util.Map;

import org.apache.camel.Processor;
import uk.co.revsys.esb.component.MappedProcessorComponent;

public class StripeComponent extends MappedProcessorComponent{

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey){
        this.apiKey = apiKey;
    }
    
    @Override
    protected void populateMappings(Map<String, Class<? extends Processor>> mappings) {
        mappings.put("customer", RetrieveCustomerProcessor.class);
        mappings.put("invoices", RetrieveInvoicesProcessor.class);
        mappings.put("createCustomer", CreateCustomerProcessor.class);
        mappings.put("deleteCustomer", DeleteCustomerProcessor.class);
        mappings.put("createSubscription", CreateSubscriptionProcessor.class);
        mappings.put("createCard", CreateCardProcessor.class);
        mappings.put("deleteCard", DeleteCardProcessor.class);
        mappings.put("setDefaultCard", SetDefaultCardProcessor.class);
    }

    @Override
    protected void configureProcessor(Processor processor) {
        System.out.println("configure");
        ((StripeProcessor)processor).setApiKey(apiKey);
    }
    
}
