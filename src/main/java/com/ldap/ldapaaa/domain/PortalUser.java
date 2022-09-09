package com.ldap.ldapaaa.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortalUser implements Serializable {
	private static final long serialVersionUID = 8257090513210333742L;

	private String username;
	private List<String> grantedAuthorities;
}
