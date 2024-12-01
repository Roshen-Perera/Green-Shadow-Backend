package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Log")
public class Log implements SuperEntity {
    @Id
    private String logCode;
    private String logDate;
    private String details;

    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @ManyToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private Field field;
    @ManyToOne
    @JoinColumn(name = "cropCode", nullable = false)
    private Crop crop;
    @ManyToOne
    @JoinColumn(name = "staffId", nullable = false)
    private Staff staff;
}
