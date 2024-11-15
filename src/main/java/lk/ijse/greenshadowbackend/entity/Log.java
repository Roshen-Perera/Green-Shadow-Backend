package lk.ijse.greenshadowbackend.entity;

import jakarta.persistence.*;
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
public class Log {
    @Id
    private String logCode;
    private Date logDate;
    private String details;
    private String observedImage;

    @ManyToMany
    @JoinTable(
            name = "log-field_details",
            joinColumns = @JoinColumn(name = "log_code"),
            inverseJoinColumns = @JoinColumn(name = "field_code")
    )
    private List<Field> fields;
    @ManyToMany
    @JoinTable(
            name = "log-crop_details",
            joinColumns = @JoinColumn(name = "log_code"),
            inverseJoinColumns = @JoinColumn(name = "crop_code")
    )
    private List<Crop> crops;
    @ManyToMany
    @JoinTable(
            name = "log_staff-details",
            joinColumns = @JoinColumn(name = "log_code"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<Staff> staff;
}
