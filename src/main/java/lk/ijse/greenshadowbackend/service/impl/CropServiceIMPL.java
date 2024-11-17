package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.dao.CropDAO;
import lk.ijse.greenshadowbackend.dto.CropStatus;
import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.entity.impl.Crop;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CropServiceIMPL implements CropService {

    @Autowired
    private CropDAO cropDAO;

    @Autowired
    private Mapping cropMapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        Crop saveField = cropDAO.save(cropMapping.toCropEntity(cropDTO));
        if(saveField == null){
            throw new RuntimeException("Crop not saved");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return List.of();
    }

    @Override
    public CropStatus getCrop(String cropId) {
        return null;
    }

    @Override
    public void deleteCrop(String cropId) {

    }

    @Override
    public void updateCrop(String cropId, CropDTO cropDTO) {

    }
}
