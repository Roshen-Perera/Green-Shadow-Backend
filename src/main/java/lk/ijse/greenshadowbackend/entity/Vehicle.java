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
@Table(name = "Vehicle")
public class Vehicle {
    @Id
    private String vehicleCode;
    private String licensePlate;
    private String category;
    private String fuelType;
    private String status;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff assignedStaff;
    private String remarks;
}
