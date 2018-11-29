package com.blockchain.api.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.blockchain.api.CustomerController;
import com.blockchain.api.HealthCheck;
import com.blockchain.api.RemittanceController;
import com.blockchain.api.ScreeninglistController;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		packages("com");
        register(HealthCheck.class);
        register(CustomerController.class);
        register(ScreeninglistController.class);
        register(RemittanceController.class);
    }
}
