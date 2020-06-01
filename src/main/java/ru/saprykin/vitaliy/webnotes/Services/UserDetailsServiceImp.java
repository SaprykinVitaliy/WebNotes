package ru.saprykin.vitaliy.webnotes.Services;


import java.util.HashSet;
import java.util.Set;


/*@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private DBAuthAgent dbAuthAgent;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
User user = dbAuthAgent.findByUsername(username);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("user"));

        return new org.springframework.security.core.userdetails.User("login", "password", grantedAuthoritySet);


        //User user = dbAuthAgent.findUserByUsername(username);
        User user = userService.findByUsername(username);
org.springframework.security.core.userdetails.User.UserBuilder builder = null;

        UserDetails userDetails = null;
        if (user != null) {
builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());

            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority("user"));

builder.authorities(roles);

            userDetails =
                    new org.springframework.security.core.userdetails.User(user.getLogin(),
                            user.getPassword(),
                            roles);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return userDetails;
    }
}*/



