package com.ldap.ldapaaa.mapper;

import com.ldap.ldapaaa.domain.Group;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * Created by boli on 2017-02-21.
 */
public class GroupAttributesMapper implements AttributesMapper<Group> {

  public Group mapFromAttributes(Attributes attrs) throws NamingException {
    Group group = new Group();

    group.setName((String)attrs.get("cn").get());
    group.setUid((String)attrs.get("gidnumber").get());

    return group;
  }

}