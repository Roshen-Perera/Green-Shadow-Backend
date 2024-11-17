package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Equipment")
public class Equipment implements SuperEntity {
    @Id
    private String equipmentId;
    private String name;
    private String type;
    private String status;
//    @ManyToOne
//    @JoinColumn(name = "staff_id")
//    private Staff assignedStaff;
//
//    @ManyToOne
//    @JoinColumn(name = "field_code")
//    private Field assignedField;
}