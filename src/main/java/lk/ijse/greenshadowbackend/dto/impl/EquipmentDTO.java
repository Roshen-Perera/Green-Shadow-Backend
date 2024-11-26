package lk.ijse.greenshadowbackend.dto.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.dto.EquipmentStatus;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Equipment")
public class EquipmentDTO implements EquipmentStatus {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String equipmentStatus;
    private String staffId;
    private String fieldCode;
}
