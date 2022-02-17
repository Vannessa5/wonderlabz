package za.co.wonderbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //User Model From DB
        za.co.wonderbank.models.User existingUser = userService.findByUsername(username);

        //User That Spring comes with.
        User user = null;
        if (existingUser != null) {
            user = new User(existingUser.getUsername(), existingUser.getPassword(), new ArrayList<>());
            return user;
        }
        return user;
    }
}
