package com.example.gobusbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gobusbooking.repository.AdminRepository;



@Service
public class AdminServiceImpl implements AdminService {
	
	
	@Autowired
	AdminRepository adminRepository;
	
	
	Logger logger= LoggerFactory.getLogger(AdminServiceImpl.class);
	
	
	
	
	  @Override
	    public Boolean loginAdmin(int adminId, String password) {
	        logger.info("Logging in admin with ID: {}", adminId);
	        return adminRepository.loginAdmin(adminId, password);
	    }

}
