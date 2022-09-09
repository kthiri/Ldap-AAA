package com.ldap.ldapaaa.repository;

import com.ldap.ldapaaa.domain.Group;
import com.ldap.ldapaaa.domain.Group2;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by boli on 2017-02-20.
 */
@Component
public interface GroupRepository {
  List<Group> getAllGroups();

  List<Group> userRoles(String user);

    List<Group2> usersRoles(String user);
}
