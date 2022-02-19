package za.co.wonderbank.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import za.co.wonderbank.filters.JwtUtil;
import za.co.wonderbank.models.AuthenticationRequest;
import za.co.wonderbank.models.AuthenticationResponse;
import za.co.wonderbank.models.User;
import za.co.wonderbank.services.MyUserDetailsService;
import za.co.wonderbank.services.UserService;

import java.time.LocalDateTime;


@RestController
@CrossOrigin(origins = "*")
public class AuthenticateResource {
//trace logs for calls made

    private Logger logger = LoggerFactory.getLogger(AuthenticateResource.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        String jwtToken = null;
        try {
            logger.info("Authentication Body { } " + authenticationRequest.toString());

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));

            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            jwtToken = this.jwtUtil.generateToken(userDetails);

            if (userDetails != null) {
                User user = this.userService.findByUsername(authenticationRequest.getUsername());
                user.setLastLoginDate(LocalDateTime.now());
                userService.save(user);
            }
        } catch (BadCredentialsException b) {
            logger.info("Incorrect Username or Password  :{ } ", authenticationRequest);
            return ResponseEntity.badRequest().body(new Exception("Incorrect Username or Password ").getMessage());
        }
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
