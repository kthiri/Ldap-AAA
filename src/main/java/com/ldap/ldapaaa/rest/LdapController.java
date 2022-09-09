package com.ldap.ldapaaa.rest;

import com.ldap.ldapaaa.domain.Group;
import com.ldap.ldapaaa.domain.Person;
import com.ldap.ldapaaa.repository.GroupRepository;
import com.ldap.ldapaaa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LdapController {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private GroupRepository groupRepository;

  @RequestMapping("/groups")
  public ResponseEntity<List<Group>> groups() {

    List<Group> groups = groupRepository.getAllGroups();

    return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/dn")
  public ResponseEntity<?> persons(@RequestParam("dn") final String dn) {

    List<Person> persons = personRepository.getAllPssersons(dn);

    return new ResponseEntity<>(persons, HttpStatus.OK);
  }

  @RequestMapping("/persons")
  public ResponseEntity<List<Person>> persons() {

    List<Person> persons = personRepository.getAllPersons();

    return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
  }
}
