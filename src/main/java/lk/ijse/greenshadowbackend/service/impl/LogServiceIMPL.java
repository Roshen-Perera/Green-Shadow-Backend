package lk.ijse.greenshadowbackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackend.DataPersistException;
import lk.ijse.greenshadowbackend.customStatusCodes.SelectedLogErrorStatus;
import lk.ijse.greenshadowbackend.dao.LogDAO;
import lk.ijse.greenshadowbackend.dto.LogStatus;
import lk.ijse.greenshadowbackend.dto.impl.LogDTO;
import lk.ijse.greenshadowbackend.entity.impl.Log;
import lk.ijse.greenshadowbackend.exception.LogNotFoundException;
import lk.ijse.greenshadowbackend.service.LogService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogServiceIMPL implements LogService {
   @Autowired
   private LogDAO logDao;
   @Autowired
   private Mapping logMapping;

    @Override
    public void saveLog(LogDTO logDTO) {
        Log savedLog = logDao.save(logMapping.toLogEntity(logDTO));
        if(savedLog == null){
            throw new DataPersistException("Log not saved");
        }
    }

    @Override
    public List<LogDTO> getAllLogs() {
        return logMapping.asLogDTOList( logDao.findAll());
    }

    @Override
    public LogStatus getLog(String logId) {
        if(logDao.existsById(logId)){
            var selectedLog = logDao.getReferenceById(logId);
            return logMapping.toLogDTO(selectedLog);
        }else {
            return new SelectedLogErrorStatus(404,"Selected log not found");
        }
    }

    @Override
    public void deleteLog(String logId) {
        Optional<Log> foundLog = logDao.findById(logId);
        if (!foundLog.isPresent()) {
            throw new LogNotFoundException("Log not found");
        }else {
            logDao.deleteById(logId);
        }
    }

    @Override
    public void updateLog(String logId, LogDTO logDTO) {

    }
}
