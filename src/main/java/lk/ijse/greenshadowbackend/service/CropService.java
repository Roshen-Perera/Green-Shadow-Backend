package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.CropStatus;
import lk.ijse.greenshadowbackend.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    CropStatus getCrop(String cropId);
    void deleteCrop(String cropId);
    void updateCrop(String cropId, CropDTO cropDTO);
}
