package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.LogStatus;
import lk.ijse.greenshadowbackend.dto.impl.LogDTO;

import java.util.List;

public interface LogService {
    void saveLog(LogDTO logDTO);
    List<LogDTO> getAllLogs();
    LogStatus getLog(String logId);
    void deleteLog(String logId);
    void updateLog(String logId, LogDTO logDTO);
}
