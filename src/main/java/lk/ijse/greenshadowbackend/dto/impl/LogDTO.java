package lk.ijse.greenshadowbackend.dto.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.dto.LogStatus;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lk.ijse.greenshadowbackend.entity.impl.Crop;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Log")
public class LogDTO implements LogStatus {
    @Id
    private String logCode;
    private Date logDate;
    private String details;
    private String observedImage;
    private String fieldCode;
    private String cropCode;
    private String staffId;
}
