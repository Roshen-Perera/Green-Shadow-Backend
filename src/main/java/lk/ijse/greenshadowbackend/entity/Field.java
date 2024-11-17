package lk.ijse.greenshadowbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class Field {
    @Id
    private String fieldCode;
    private String fieldName;
    private String location;
    private String extent;
    @ManyToOne
    @JoinColumn(name = "crop_code")
    private Crop crop;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;
}
