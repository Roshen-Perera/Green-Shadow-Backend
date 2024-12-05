package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.UserStatus;
import lk.ijse.greenshadowbackend.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserStatus getUser(String userId);

    void deleteUser(String userId);

    UserDetailsService userDetailsService();
    void updateUser(UserDTO buildUserDTO);

    void saveUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();
}
