package com.Auditing_Tutorial.demo.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        // get context
        // and then do 2,3 step then get the username
        // will do when learning spring security
        return Optional.of("Mayank Mishra");
    }
}
