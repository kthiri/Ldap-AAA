package com.ldap.ldapaaa.rest;

import com.ldap.ldapaaa.domain.Group;
import com.ldap.ldapaaa.domain.Group2;
import com.ldap.ldapaaa.domain.Person;
import com.ldap.ldapaaa.domain.dto.AuthenticationResponse;
import com.ldap.ldapaaa.repository.GroupRepository;
import com.ldap.ldapaaa.repository.PersonRepository;
import com.ldap.ldapaaa.util.JwtUtils;
import com.ldap.ldapaaa.util.LdapAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/auth-server")
public class AuthenticationController
{
    private static final String USER_DISABLED = "USER DISABLED";
    private static final String INVALID_CREDENTIALS = "INVALID CREDENTIALS";

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Autowired
    private LdapAuthenticationProvider authenticationProvider;

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtUtils  generateToken;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    GroupRepository groupRepository;


    @PostMapping
    public ResponseEntity<?> authenticateRequest() throws Exception{

        authenticate(SecurityContextHolder.getContext().getAuthentication());
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        List<Person> personList=personRepository.getAllPssersons(username);
        List<String> userRoles=new ArrayList<>();
        for (int i=0;i< personList.size();i++){
            List<Group2> roles=groupRepository.usersRoles(personList.get(i).getIdNumber());
            for (int k=0;k<roles.size();k++){
                userRoles.add(roles.get(k).getName());
            }

        }
       // List<Person> userRoles=groupRepository.userRoles(username);

        final String token = generateToken.createJWTToken(username,this.jwtSecret, 1000, userRoles);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private void authenticate(Authentication auth) throws Exception {
        try {
            authenticationProvider.authenticate(auth);
        } catch (DisabledException e) {
            throw new Exception(USER_DISABLED, e);
        } catch (BadCredentialsException e) {
            throw new Exception(INVALID_CREDENTIALS, e);
        }
    }

}