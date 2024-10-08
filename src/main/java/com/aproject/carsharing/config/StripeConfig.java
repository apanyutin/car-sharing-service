package com.aproject.carsharing.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {
    //@Value("${stripe.api.key}")
    private String stripeApiKey = "sk_test_51Q6sbNRsY94Kc7WAmC5C6DWwZWT"
            + "uuAEVDjTx64tSxeGamT8gkyHR56X4Jnun1Csx3O2G1G8ZoMxoPwP2rvIBZgwv00nD2gERmC";

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }
}
