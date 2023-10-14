package com.aos.infrustructure.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface AuthoritiesService {
    Collection<? extends GrantedAuthority> authorities();
}
