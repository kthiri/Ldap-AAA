package com.ldap.ldapaaa.mapper;

import com.ldap.ldapaaa.domain.Person;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * Created by boli on 2017-02-21.
 */
public class PersonAttributesMapper implements AttributesMapper<Person> {

  public Person mapFromAttributes(Attributes attrs) throws NamingException {
    Person person = new Person();

    person.setFullName((String)attrs.get("cn").get());
    person.setLastName((String)attrs.get("sn").get());
    person.setIdNumber((String)attrs.get("gidnumber").get());

    return person;
  }

}