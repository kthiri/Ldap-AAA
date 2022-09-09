package com.ldap.ldapaaa.repository;

import com.ldap.ldapaaa.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by boli on 2017-02-20.
 */
@Component
public interface PersonRepository {

  public List<Person> getAllPssersons(String uid);

  List<Person> getAllPersons();

}
