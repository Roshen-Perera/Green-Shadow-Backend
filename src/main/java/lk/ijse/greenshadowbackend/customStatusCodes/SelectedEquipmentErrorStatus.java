package lk.ijse.greenshadowbackend.customStatusCodes;

import lk.ijse.greenshadowbackend.dto.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedEquipmentErrorStatus implements EquipmentStatus {
    private int statusCode;
    private String statusMessage;
}
