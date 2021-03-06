package uk.co.revsys.stripe.camel;

import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.StripeObject;
import java.util.Iterator;
import org.apache.camel.Exchange;

public class DeleteCardProcessor extends StripeProcessor {

    private String customerId;
    private String cardId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public StripeObject doProcess(Exchange exchng) throws Exception {
        Customer customer = Customer.retrieve(getCustomerId(), getApiKey());
        Iterator<Card> cardIterator = customer.getCards().getData().iterator();
        while(cardIterator.hasNext()){
            Card card = cardIterator.next();
            if (card.getId().equals(getCardId())) {
                card.delete(getApiKey());
                break;
            }
        }
        return Customer.retrieve(getCustomerId(), getApiKey());
    }

}
