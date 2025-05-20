package bg.nvna.nvnachat.service.impl;

import bg.nvna.nvnachat.dto.auth.AuthRegisterRequest;
import bg.nvna.nvnachat.model.User;
import bg.nvna.nvnachat.repository.UserRepository;
import bg.nvna.nvnachat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(AuthRegisterRequest user) {
        return userRepository.save(new User(null, user.getUsername(), 0, user.getEmail(), user.getPassword()));
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
