package com.ldap.ldapaaa.repository;

import com.ldap.ldapaaa.domain.Person;
import com.ldap.ldapaaa.mapper.PersonAttributesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by boli on 2017-02-20.
 */
@Component
public class PersonRepositoryImpl implements PersonRepository {

  public final String BASE_DN = "dc=itksoluciones,dc=com";

  @Autowired
  private LdapTemplate ldapTemplate;



  public List<Person> getAllPersons() {
    return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
  }

  public List<Person> getAllPssersons(String uid) {
    return ldapTemplate.search(query().where("uid").is(uid), new PersonAttributesMapper());
  }

}
