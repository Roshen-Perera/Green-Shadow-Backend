package lk.ijse.greenshadowbackend.customStatusCodes;

import lk.ijse.greenshadowbackend.dto.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedCropErrorStatus implements CropStatus {
    private int statusCode;
    private String statusMessage;
}
