package bg.nvna.nvnachat.service;

import bg.nvna.nvnachat.dto.auth.AuthRegisterRequest;
import bg.nvna.nvnachat.model.User;

import java.util.List;

public interface UserService {

    User register(AuthRegisterRequest user);

    boolean isEmailExist(String email);

    User findByEmail(String email);

    List<User> findAll();

    User update(User user);
}
