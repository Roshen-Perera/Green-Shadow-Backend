package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.DataPersistException;
import lk.ijse.greenshadowbackend.customStatusCodes.SelectedVehicleErrorStatus;
import lk.ijse.greenshadowbackend.dto.VehicleStatus;
import lk.ijse.greenshadowbackend.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackend.exception.VehicleNotFoundException;
import lk.ijse.greenshadowbackend.service.VehicleService;
import lk.ijse.greenshadowbackend.util.RegexProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    static Logger logger =  LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;


    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(
            @RequestPart("vehicleCode") String vehicleCode,
            @RequestPart("licensePlate") String licensePlate,
            @RequestPart("category") String category,
            @RequestPart("fuelType") String fuelType,
            @RequestPart("status") String status,
            @RequestPart("staff") String staff,
            @RequestPart("remarks") String remarks
    ) {
        try {
            if (!RegexProcess.vehicleIdMatcher(vehicleCode)) {
                logger.info("Vehicle ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleLicensePlateMatcher(licensePlate)) {
                logger.info("License plate is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleCategoryMatcher(category)) {
                logger.info("Category is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleFuelTypeMatcher(fuelType)) {
                logger.info("Fuel type is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleStatusMatcher(status)) {
                logger.info("Status is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.staffIdMatcher(staff)) {
                logger.info("Staff ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
//            if(!RegexProcess.vehicleRemarksMatcher(remarks)){
//                logger.info("Remarks is not valid");
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }



            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleDTO.setVehicleCode(vehicleCode);
            vehicleDTO.setLicensePlate(licensePlate);
            vehicleDTO.setCategory(category);
            vehicleDTO.setFuelType(fuelType);
            vehicleDTO.setStatus(status);
            vehicleDTO.setStaffId(staff);
            vehicleDTO.setRemarks(remarks);
            vehicleService.saveVehicle(vehicleDTO);
            logger.info("Vehicle saved successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{vehicleID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getSelectedVehicle(@PathVariable ("vehicleID") String vehicleId){
        if (!RegexProcess.vehicleIdMatcher(vehicleId)) {
            logger.error("Vehicle ID is not valid");
            return new SelectedVehicleErrorStatus(1,"Vehicle ID is not valid");
        }
        logger.info("Vehicle fetched");
        return vehicleService.getVehicle(vehicleId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() {
        logger.info("All vehicles fetched");
        return vehicleService.getAllVehicles();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @DeleteMapping(value = "/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable ("vehicleId") String vehicleId){
        try {
            if (!RegexProcess.vehicleIdMatcher(vehicleId)) {
                logger.info("Vehicle ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("Vehicle deleted");
            vehicleService.deleteVehicle(vehicleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(
            @RequestPart("vehicleCode") String vehicleCode,
            @RequestPart("licensePlate") String licensePlate,
            @RequestPart("category") String category,
            @RequestPart("fuelType") String fuelType,
            @RequestPart("status") String status,
            @RequestPart("assignedStaff") String assignedStaff,
            @RequestPart("remarks") String remarks
    ) {
        try {
            if (!RegexProcess.vehicleIdMatcher(vehicleCode)) {
                logger.info("Vehicle ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleLicensePlateMatcher(licensePlate)) {
                logger.info("License plate is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleCategoryMatcher(category)) {
                logger.info("Category is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleFuelTypeMatcher(fuelType)) {
                logger.info("Fuel type is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.vehicleStatusMatcher(status)) {
                logger.info("Status is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (!RegexProcess.staffIdMatcher(assignedStaff)) {
                logger.info("Staff ID is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            VehicleDTO vehicleDTO = new VehicleDTO();
            vehicleDTO.setVehicleCode(vehicleCode);
            vehicleDTO.setLicensePlate(licensePlate);
            vehicleDTO.setCategory(category);
            vehicleDTO.setFuelType(fuelType);
            vehicleDTO.setStatus(status);
            vehicleDTO.setStaffId(assignedStaff);
            vehicleDTO.setRemarks(remarks);
            logger.info("Vehicle updated");
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
