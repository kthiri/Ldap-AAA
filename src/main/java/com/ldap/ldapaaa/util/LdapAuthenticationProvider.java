package com.ldap.ldapaaa.util;

import org.springframework.core.env.Environment;
import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class LdapAuthenticationProvider implements AuthenticationProvider
{
    private Environment environment;
    public LdapAuthenticationProvider(Environment environment) {
        this.environment = environment;
    }
    private LdapContextSource contextSource;
    private LdapTemplate ldapTemplate;
    private void initContext()
    {
        contextSource = new LdapContextSource();
        contextSource.setUrl(("ldap://190.144.111.186:4489/dc=itksoluciones,dc=com"));
        contextSource.setAnonymousReadOnly(true);
        contextSource.setUserDn("uid={0},ou=people");
        contextSource.afterPropertiesSet();

        ldapTemplate = new LdapTemplate(contextSource);
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        initContext();
        Filter filter = new EqualsFilter("uid", authentication.getName());
        Boolean authenticate = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter.encode(), authentication.getCredentials().toString());
        if (authenticate)
        {
            UserDetails userDetails = new User(authentication.getName(), authentication.getCredentials().toString()
                    , new ArrayList<>());
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                    authentication.getCredentials().toString(), new ArrayList<>());
            return auth;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}