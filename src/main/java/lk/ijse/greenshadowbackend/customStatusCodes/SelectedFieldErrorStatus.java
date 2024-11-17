package lk.ijse.greenshadowbackend.customStatusCodes;

import lk.ijse.greenshadowbackend.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedFieldErrorStatus implements FieldStatus {
    private int statusCode;
    private String statusMessage;
}
