package lk.ijse.greenshadowbackend.dto.impl;
import lk.ijse.greenshadowbackend.dto.UserStatus;
import lk.ijse.greenshadowbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserStatus {
    private Role role;
    private String email;
    private String password;
}
