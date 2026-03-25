package com.autocenter.inventory.helpers;

import com.autocenter.inventory.enums.SubscriptionType;
import com.autocenter.inventory.model.Dealer;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class DealersGenerator {


    public List<Dealer> generateDealers() {
        List<Dealer> dealers = new LinkedList<>();
        for (int i = 0; i < 55; i++) {
            Dealer dealer = new Dealer();
            dealer.setSubscriptionType((i & 1) == 0 ? SubscriptionType.BASIC : SubscriptionType.PREMIUM);
            dealer.setEmail(generateRandomEmail());
            dealer.setName(dealer.getEmail().substring(0, dealer.getEmail().indexOf("@")));
            dealer.setTenantId(new Random().nextInt(Integer.MAX_VALUE) + "");
            dealers.add(dealer);
        }
        return dealers;
    }

    String generateName(){
        int length = new Random().nextInt(15) + 5;
        String email = "";
        for (int i = 0; i < length; i++) {
            char letter = (char) ('a' + new Random().nextInt(26));
            email += letter;
        }
        return email;
    }
    private String generateRandomEmail() {

        return generateName() + "@";
    }
}
