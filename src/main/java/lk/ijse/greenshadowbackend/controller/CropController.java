package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.DataPersistException;
import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import lk.ijse.greenshadowbackend.util.RegexProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            @RequestPart ("cropSeason") String cropSeason
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