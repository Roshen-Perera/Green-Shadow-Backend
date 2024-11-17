package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.StaffStatus;
import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getAllStaffs();
    StaffStatus getStaff(String staffId);
    void deleteStaff(String staffId);
    void updateStaff(String staffId, StaffDTO staffDTO);
}
