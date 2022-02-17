package za.co.wonderbank.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.wonderbank.models.User;
import za.co.wonderbank.repositories.UserRepository;
import za.co.wonderbank.services.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        log.info("Request To Save user", user);
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        log.info("Request To get All user");
        Page<User> all = userRepository.findAll(pageable);
        return all;
    }

    @Override
    public List<User> findAll() {
        log.info("Request To get all users");
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        log.info("Request To get by username");
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(Long id) {
        log.info("Request To delete user by id");
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        log.info("Request To delete user");
        userRepository.delete(user);
    }

    @Override
    public User updateFailedAttempts(Integer failedAttempts, String username) {
        log.info("Request To update Failed Attempts");
        return this.userRepository.updateFailedAttempts(failedAttempts, username);
    }

    @Override
    public void lockUser(User user) {
        user.setUserLocked(true);
        user.setLockTime(LocalDateTime.now());
        this.userRepository.save(user);
    }
}
