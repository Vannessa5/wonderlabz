package za.co.wonderbank.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wonderbank.models.User;
import za.co.wonderbank.services.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserResource {
    private final static Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> save(@RequestBody User user) {
        log.info("Request To save user", user);
        user.setLastLoginDate(LocalDateTime.now());
        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody User user) {
        log.info("Request To save user", user);
        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/user")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping("/user/unlock-user")
    public ResponseEntity<User> unlockUserAccount(@RequestBody User user) {
        user.setUserLocked(false);
        user.setLockTime(null);
        user.setEnabled(true);
        User result = this.userService.save(user);
        return ResponseEntity.ok(result);
    }
}
