package com.example.gobusbooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gobusbooking.entity.LoginDto;
import com.example.gobusbooking.service.AdminService;
import com.example.gobusbooking.service.BusService;
import com.example.gobusbooking.service.UserService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	Logger logger= LoggerFactory.getLogger(AdminController.class);
	
	
	
	  @ApiOperation(value = "Admin login")
	    @PostMapping(value = "/loginadmin")
	    public Boolean loginAdmin(@RequestBody LoginDto dto) {
	        logger.info("Admin login request for ID: {}", dto.getId());
	        Boolean adminPersisted = adminService.loginAdmin(dto.getId(), dto.getPassword());
	        logger.info("Admin login result for ID {}: {}", dto.getId(), adminPersisted);
	        return adminPersisted;
	    }

}
