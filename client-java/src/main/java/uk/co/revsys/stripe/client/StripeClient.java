package uk.co.revsys.stripe.client;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Plan;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
import uk.co.revsys.utils.http.HttpClient;
import uk.co.revsys.utils.http.HttpRequest;
import uk.co.revsys.utils.http.HttpResponse;

public class StripeClient {

    public StripeClient() {
    }
    
    public String plans() throws IOException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
        Stripe.apiKey = "sk_test_4qo3vbZaV6CI9l3ITyox2St3";
        String response = Plan.all(new HashMap<String, Object>()).toString();
        return response;
    }
    


}
