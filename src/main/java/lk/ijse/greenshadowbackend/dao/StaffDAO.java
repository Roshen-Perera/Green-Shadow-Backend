package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.impl.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDAO extends JpaRepository<Staff, String> {

}
