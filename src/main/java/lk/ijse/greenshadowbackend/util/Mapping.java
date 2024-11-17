package lk.ijse.greenshadowbackend.util;

import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackend.entity.impl.Crop;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    public Field toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, Field.class);
    }
    public FieldDTO toFieldDTO(Field fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }
    public List<FieldDTO> asFieldDTOList(List<Field> fieldEntities) {
        return modelMapper.map(fieldEntities, new TypeToken<List<FieldDTO>>() {}.getType());
    }

    public Crop toCropEntity(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, Crop.class);
    }
    public CropDTO toCropDTO(Crop cropEntity) {
        return modelMapper.map(cropEntity, CropDTO.class);
    }
    public List<CropDTO> asCropDTOList(List<Crop> cropEntities) {
        return modelMapper.map(cropEntities, new TypeToken<List<CropDTO>>() {}.getType());
    }

    public Staff toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, Staff.class);
    }
    public StaffDTO toStaffDTO(Staff staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }
    public List<StaffDTO> asStaffDTOList(List<Staff> staffEntities) {
        return modelMapper.map(staffEntities, new TypeToken<List<StaffDTO>>() {}.getType());
    }
}
