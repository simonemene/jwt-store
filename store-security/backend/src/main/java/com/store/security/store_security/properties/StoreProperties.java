package com.store.security.store_security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "store")
public class StoreProperties {

	private String securityAllowedOrigin;

	private String jwtSecretKeyValue;

	private String jwtHeader;
}
