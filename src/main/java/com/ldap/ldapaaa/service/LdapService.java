package com.ldap.ldapaaa.service;

import com.ldap.ldapaaa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by boli on 2017-02-21.
 */
@Service
public class LdapService {

  @Autowired
  PersonRepository personRepository;



}
