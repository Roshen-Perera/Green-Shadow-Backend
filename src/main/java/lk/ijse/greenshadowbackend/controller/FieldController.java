package lk.ijse.greenshadowbackend.controller;
import lk.ijse.greenshadowbackend.DataPersistException;
import lk.ijse.greenshadowbackend.customStatusCodes.SelectedFieldErrorStatus;
import lk.ijse.greenshadowbackend.dto.FieldStatus;
import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackend.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.service.FieldService;
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

import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
public class FieldController {
    static Logger logger =  LoggerFactory.getLogger(FieldController.class);

    @Autowired
   private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart ("fieldCode") String fieldCode,
            @RequestPart ("fieldName") String fieldName,
            @RequestPart ("location") String location,
            @RequestPart ("extent") String extent,
           // @RequestPart ("crop_code") String crop_code,
           // @RequestPart ("staff_id") String staff_id,
            @RequestPart ("fieldImage1") MultipartFile fieldImage1,
            @RequestPart ("fieldImage2") MultipartFile fieldImage2
    ) {
        String base64fieldImage1 = "";
        String base64fieldImage2 = "";
        try {
            byte[] imageBytes1 = fieldImage1.getBytes();
            base64fieldImage1 = AppUtil.picToBase64(imageBytes1);

            byte[] imageBytes2 = fieldImage2.getBytes();
            base64fieldImage2 = AppUtil.picToBase64(imageBytes2);

            if (!RegexProcess.fieldIdMatcher(fieldCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setLocation(location);
            fieldDTO.setExtent(extent);
           // fieldDTO.s(crop_code);
            //fieldDTO.setStaff_id(staff_id);
            fieldDTO.setFieldImage1(base64fieldImage1);
            fieldDTO.setFieldImage2(base64fieldImage2);
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{fieldID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getSelectedField(@PathVariable ("fieldID") String fieldId){
        if (!RegexProcess.fieldIdMatcher(fieldId)) {
            return new SelectedFieldErrorStatus(1,"Field ID is not valid");
        }
        return fieldService.getField(fieldId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @DeleteMapping(value = "/{fieldId}")
    public ResponseEntity<Void> deleteField(@PathVariable ("fieldId") String fieldId){
        try {
            if (!RegexProcess.fieldIdMatcher(fieldId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deleteField(fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateField(
            @RequestPart ("fieldCode") String fieldCode,
            @RequestPart ("fieldName") String fieldName,
            @RequestPart ("location") String location,
            @RequestPart ("extent") String extent,
            @RequestPart ("cropCode") String crop_code,
            //   @RequestPart ("staff_id") String staff_id,
            @RequestPart ("fieldImage1") MultipartFile fieldImage1,
            @RequestPart ("fieldImage2") MultipartFile fieldImage2
    ) {
        String base64fieldImage1 = "";
        String base64fieldImage2 = "";
        try {
            byte[] imageBytes1 = fieldImage1.getBytes();
            base64fieldImage1 = AppUtil.picToBase64(imageBytes1);

            byte[] imageBytes2 = fieldImage2.getBytes();
            base64fieldImage2 = AppUtil.picToBase64(imageBytes2);

            if (!RegexProcess.fieldIdMatcher(fieldCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setLocation(location);
            fieldDTO.setExtent(extent);
            //fieldDTO.setCropCode(crop_code);
            //fieldDTO.setStaff_id("N/A");
            fieldDTO.setFieldImage1(base64fieldImage1);
            fieldDTO.setFieldImage2(base64fieldImage2);
            fieldService.saveField(fieldDTO);
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
