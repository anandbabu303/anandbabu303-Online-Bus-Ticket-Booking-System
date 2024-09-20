package com.example.gobusbooking.repository;

import org.springframework.stereotype.Repository;

import com.example.gobusbooking.entity.Admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Repository
public class AdminRepositoryImpl implements AdminRepository {
	
	@PersistenceContext
	EntityManager em;
	
	

    public Boolean loginAdmin(int adminId, String password) {
        String jpql1 = "select a from Admin a where a.adminId=:id and a.password=:pass";
        TypedQuery<Admin> query = em.createQuery(jpql1, Admin.class);
        query.setParameter("id", adminId);
        query.setParameter("pass", password);
        Admin admin = null;
        try {
            admin = query.getSingleResult();
        } catch (Exception e) {
        }
        if (admin == null) {
            return false;
        }
        return true;
    }



}
