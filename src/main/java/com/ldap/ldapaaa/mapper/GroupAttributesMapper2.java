package com.ldap.ldapaaa.mapper;

import com.ldap.ldapaaa.domain.Group;
import com.ldap.ldapaaa.domain.Group2;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * Created by boli on 2017-02-21.
 */
public class GroupAttributesMapper2 implements AttributesMapper<Group2> {

  public Group2 mapFromAttributes(Attributes attrs) throws NamingException {
    Group2 group = new Group2();

    group.setName((String)attrs.get("cn").get());
    //group.setUid((String)attrs.get("gidnumber").get());

    return group;
  }

}