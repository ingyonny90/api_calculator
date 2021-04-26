package com.api.calculator.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    private static final String DEFAULT_USER = "appgate_user";

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(DEFAULT_USER);
    }
}
