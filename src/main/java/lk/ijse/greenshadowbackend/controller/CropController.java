package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.DataPersistException;
import lk.ijse.greenshadowbackend.customStatusCodes.SelectedCropErrorStatus;
import lk.ijse.greenshadowbackend.dto.CropStatus;
import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.exception.CropNotFoundException;
import lk.ijse.greenshadowbackend.service.CropService;
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
@RequestMapping("/api/v1/crop")
public class CropController {

    static Logger logger =  LoggerFactory.getLogger(CropController.class);

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart ("cropCode") String cropCode,
            @RequestPart ("cropName") String cropName,
            @RequestPart ("cropScientificName") String cropScientificName,
            @RequestPart ("cropImage") MultipartFile cropImage,
            @RequestPart ("cropCategory") String cropCategory,
            @RequestPart ("cropSeason") String cropSeason,
            @RequestPart ("fieldCode") String fieldCode
    ){
        String base64CropImage = "";
        try{
            byte[] imageBytes = cropImage.getBytes();
            base64CropImage = AppUtil.picToBase64(imageBytes);

            if (!RegexProcess.cropIdMatcher(cropCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCode(cropCode);
            cropDTO.setCommonName(cropName);
            cropDTO.setScientificName(cropScientificName);
            cropDTO.setCategory(cropCategory);
            cropDTO.setSeason(cropSeason);
            cropDTO.setFieldCode(fieldCode);
            cropDTO.setImage(base64CropImage);
            cropService.saveCrop(cropDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{cropId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable ("cropId") String cropId) {
        if (!RegexProcess.cropIdMatcher(cropId)) {
            return new SelectedCropErrorStatus(1, "Crop ID is not valid");
        }
        return cropService.getCrop(cropId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() {
        return cropService.getAllCrops();
    }

    @DeleteMapping(value = "/{cropId}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropId") String cropId){
        try {
            if (!RegexProcess.cropIdMatcher(cropId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(cropId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCrop(
            @RequestPart ("cropCode") String cropCode,
            @RequestPart ("cropName") String cropName,
            @RequestPart ("cropScientificName") String cropScientificName,
            @RequestPart ("cropImage") MultipartFile cropImage,
            @RequestPart ("cropCategory") String cropCategory,
            @RequestPart ("cropSeason") String cropSeason,
            @RequestPart ("fieldCode") String fieldCode
    ){
        String base64CropImage = "";
        try{
            byte[] imageBytes = cropImage.getBytes();
            base64CropImage = AppUtil.picToBase64(imageBytes);

            if (!RegexProcess.cropIdMatcher(cropCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCode(cropCode);
            cropDTO.setCommonName(cropName);
            cropDTO.setScientificName(cropScientificName);
            cropDTO.setCategory(cropCategory);
            cropDTO.setSeason(cropSeason);
            cropDTO.setFieldCode(fieldCode);
            cropDTO.setImage(base64CropImage);
            cropService.saveCrop(cropDTO);

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
