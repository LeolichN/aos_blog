package com.aos.application.service;

import com.aos.infrustructure.security.AuthoritiesService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AuthBookService implements AuthoritiesService {

    @Override
    public Collection<? extends GrantedAuthority> authorities() {
        return Collections.EMPTY_LIST;
    }
}
