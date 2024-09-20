package com.example.gobusbooking.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository {
	
	
	public Boolean loginAdmin(int adminId, String password);


}
