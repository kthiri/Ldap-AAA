package com.ldap.ldapaaa.repository;

import com.ldap.ldapaaa.domain.Group;
import com.ldap.ldapaaa.domain.Group2;
import com.ldap.ldapaaa.mapper.GroupAttributesMapper;
import com.ldap.ldapaaa.mapper.GroupAttributesMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by boli on 2017-02-20.
 */
@Component
public class GroupRepositoryImpl implements GroupRepository {

  public final String BASE_DN = "dc=isc,dc=com;";

  @Autowired
  private LdapTemplate ldapTemplate;

  @Override
  public List<Group> getAllGroups() {
    return ldapTemplate.search(query().where("objectclass").is("posixGroup"), new GroupAttributesMapper());
  }

  @Override
  public List<Group> userRoles(String user) {
    return ldapTemplate.search(query().where("objectclass").is("posixGroup").and("gidnumber").is(user), new GroupAttributesMapper());
  }

  @Override
  public List<Group2> usersRoles(String user) {
    return ldapTemplate.search(query().where("objectclass").is("posixGroup").and("gidnumber").is(user), new GroupAttributesMapper2());
  }
}
