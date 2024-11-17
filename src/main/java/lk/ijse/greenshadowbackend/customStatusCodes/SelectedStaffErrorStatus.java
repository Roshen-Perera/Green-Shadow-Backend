package lk.ijse.greenshadowbackend.customStatusCodes;

import lk.ijse.greenshadowbackend.dto.FieldStatus;
import lk.ijse.greenshadowbackend.dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedStaffErrorStatus implements StaffStatus {
    private int statusCode;
    private String statusMessage;
}
