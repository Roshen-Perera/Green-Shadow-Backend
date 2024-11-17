package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.FieldStatus;
import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO noteDTO);
    List<FieldDTO> getAllFields();
    FieldStatus getField(String noteId);
    void deleteField(String noteId);
    void updateField(String noteId, FieldDTO noteDTO);
}
