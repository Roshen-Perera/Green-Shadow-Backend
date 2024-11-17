
package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class Field implements SuperEntity {
    @Id
    private String fieldCode;
    private String fieldName;
    private String location;
    private String extent;
    @OneToMany(mappedBy="field")
    private List<Crop> crops;
    /*@ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;*/
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;
}
