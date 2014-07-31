package uk.co.revsys.stripe.client;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Plan;
import com.stripe.model.Subscription;
import com.stripe.model.Token;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import uk.co.revsys.utils.http.HttpClient;
import uk.co.revsys.utils.http.HttpRequest;
import uk.co.revsys.utils.http.HttpResponse;

public class StripeClient {

    String apiKey;
    
    public StripeClient(String apiKey) {
        this.apiKey=apiKey;
        Stripe.apiKey = apiKey;
    }
    
    public String plans() throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
        String response = Plan.all(new HashMap<String, Object>()).toString();
        return stripJSONPrefix(response);
    }
    
    public String getCardToken(String json)throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
        Map<String, Object> tokenParams = JSONUtil.json2map(json);
        String response = Token.create(tokenParams).toString();
        return stripJSONPrefix(response);
    }
    
    public String planCreate(String json)throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
        Map<String, Object> params = JSONUtil.json2map(json);
        String response = Plan.create(params).toString();
        return stripJSONPrefix(response);
    }
    
    public String customerCreate(String json)throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
        Map<String, Object> params = JSONUtil.json2map(json);
        String response = Customer.create(params).toString();
        return stripJSONPrefix(response);
    }
    
    public String subscriptionCreate(String json)throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
        Map<String, Object> params = JSONUtil.json2map(json);
        Customer customer = Customer.retrieve((String)params.get("customerId"));
        params.remove("customerId");
        String response = customer.createSubscription(params).toString();
        return stripJSONPrefix(response);
    }
    
    public String customerInvoices(String json)throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
        Map<String, Object> invoiceParams = JSONUtil.json2map(json);
        invoiceParams.put("customer", invoiceParams.get("customerId"));
        invoiceParams.remove("customerId");
        String response = Invoice.all(invoiceParams).toString();        
        return stripJSONPrefix(response);
    }
    
    private String stripJSONPrefix(String jsonString){
        String stripped= jsonString.substring(jsonString.indexOf("JSON: ")+6);
        return stripped;
    }

}
