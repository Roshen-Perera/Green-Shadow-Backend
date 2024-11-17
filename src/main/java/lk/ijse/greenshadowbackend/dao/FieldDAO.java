package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDAO extends JpaRepository<Field, String> {

}
