package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.DataPersistException;
import lk.ijse.greenshadowbackend.customStatusCodes.SelectedStaffErrorStatus;
import lk.ijse.greenshadowbackend.dto.StaffStatus;
import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackend.entity.Gender;
import lk.ijse.greenshadowbackend.entity.Role;
import lk.ijse.greenshadowbackend.exception.StaffNotFoundException;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.RegexProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    static Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(
            @RequestPart("staffId") String staffId,
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("designation") String designation,
            @RequestPart("gender") String gender,
            @RequestPart("joinedDate") String joinedDate,
            @RequestPart("dob") String dob,
            @RequestPart("addressLine1") String addressLine1,
            @RequestPart("addressLine2") String addressLine2,
            @RequestPart("addressLine3") String addressLine3,
            @RequestPart("addressLine4") String addressLine4,
            @RequestPart("addressLine5") String addressLine5,
            @RequestPart("contactNo") String contactNo,
            @RequestPart("email") String email,
            @RequestPart("role") String role,
            @RequestPart("fieldCode") String fieldCode
    ) {
        try {
            if (!RegexProcess.staffIdMatcher(staffId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            StaffDTO staffDTO = new StaffDTO();
            staffDTO.setStaffId(staffId);
            staffDTO.setFirstName(firstName);
            staffDTO.setLastName(lastName);
            staffDTO.setDesignation(designation);
            staffDTO.setGender(Gender.valueOf(gender.toUpperCase())); // Assuming you have a Gender enum.
            staffDTO.setJoinedDate(joinedDate);
            staffDTO.setDob(dob);
            staffDTO.setAddressLine1(addressLine1);
            staffDTO.setAddressLine2(addressLine2);
            staffDTO.setAddressLine3(addressLine3);
            staffDTO.setAddressLine4(addressLine4);
            staffDTO.setAddressLine5(addressLine5);
            staffDTO.setContactNo(contactNo);
            staffDTO.setEmail(email);
            staffDTO.setRole(Role.valueOf(role.toUpperCase()));
            staffDTO.setFieldCode(fieldCode);
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getSelectedStaff(@PathVariable("staffId") String staffId) {
        if (!RegexProcess.staffIdMatcher(staffId)) {
            return new SelectedStaffErrorStatus(1, "Staff ID is not valid");
        }
        return staffService.getStaff(staffId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String staffId) {
        try {
            if (!RegexProcess.staffIdMatcher(staffId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.deleteStaff(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}