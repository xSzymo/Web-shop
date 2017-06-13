package com.shop.configuration.security;

import com.shop.configuration.security.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebAppInitializer() {
        super(SecurityConfig.class);
    }

}
