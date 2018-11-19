package com.blockchain.api.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.blockchain.api.HealthCheck;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
        register(HealthCheck.class);
    }
}
