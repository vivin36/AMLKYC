package com.blockchain.api.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.blockchain.api.CustomerController;
import com.blockchain.api.HealthCheck;
import com.blockchain.api.PaymentController;
import com.blockchain.api.ScreeninglistController;
import com.blockchain.exception.ApplicationExceptionMapper;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
        register(HealthCheck.class);
        register(CustomerController.class);
        register(ScreeninglistController.class);
        register(PaymentController.class);   
        register(ApplicationExceptionMapper.class);
    }
}
