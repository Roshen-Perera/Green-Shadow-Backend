package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.DataPersistException;
import lk.ijse.greenshadowbackend.customStatusCodes.SelectedLogErrorStatus;
import lk.ijse.greenshadowbackend.dto.LogStatus;
import lk.ijse.greenshadowbackend.dto.impl.LogDTO;
import lk.ijse.greenshadowbackend.exception.LogNotFoundException;
import lk.ijse.greenshadowbackend.service.LogService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import lk.ijse.greenshadowbackend.util.RegexProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/log")
public class LogController {

    static Logger logger =  LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(
            @RequestPart ("logCode") String logCode,
            @RequestPart ("logDate") String logDate,
            @RequestPart ("details") String details,
            @RequestPart ("observedImage") MultipartFile observedImage,
            @RequestPart ("fieldCode") String fieldCode,
            @RequestPart ("cropCode") String cropCode,
            @RequestPart ("staffId") String staffId
    ){
        String base64LogImage = "";
        try{
            byte[] imageBytes = observedImage.getBytes();
            base64LogImage = AppUtil.picToBase64(imageBytes);

            if (!RegexProcess.logIdMatcher(logCode)) {
                logger.info("Log ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            LogDTO logDTO = new LogDTO();
            logDTO.setLogCode(logCode);
            logDTO.setLogDate(logDate);
            logDTO.setDetails(details);
            logDTO.setObservedImage(base64LogImage);
            logDTO.setFieldCode(fieldCode);
            logDTO.setCropCode(cropCode);
            logDTO.setStaffId(staffId);
            logService.saveLog(logDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            logger.info("Data persist error");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{logId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public LogStatus getSelectedLog(@PathVariable ("logId") String logId) {
        if (!RegexProcess.logIdMatcher(logId)) {
            return new SelectedLogErrorStatus(1, "Log ID is not valid");
        }
        return logService.getLog(logId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> getAllLogs() {
        return logService.getAllLogs();
    }

    @DeleteMapping(value = "/{cropId}")
    public ResponseEntity<Void> deleteLog(@PathVariable ("cropId") String cropId){
        try {
            if (!RegexProcess.cropIdMatcher(cropId)) {
                logger.info("Log ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logService.deleteLog(cropId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (LogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateLog(
            @RequestPart ("logCode") String logCode,
            @RequestPart ("logDate") String logDate,
            @RequestPart ("details") String details,
            @RequestPart ("observedImage") MultipartFile observedImage,
            @RequestPart ("fieldCode") String fieldCode,
            @RequestPart ("cropCode") String cropCode,
            @RequestPart ("staffId") String staffId
    ){
        String base64LogImage = "";
        try{
            byte[] imageBytes = observedImage.getBytes();
            base64LogImage = AppUtil.picToBase64(imageBytes);

            if (RegexProcess.logIdMatcher(logCode)) {
                logger.info("Log ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            LogDTO logDTO = new LogDTO();
            logDTO.setLogCode(logCode);
            logDTO.setLogDate(logDate);
            logDTO.setDetails(details);
            logDTO.setObservedImage(base64LogImage);
            logDTO.setFieldCode(fieldCode);
            logDTO.setCropCode(cropCode);
            logDTO.setStaffId(staffId);
            logService.saveLog(logDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
