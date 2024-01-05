package com.example.mindspace.controller;

import com.example.mindspace.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service for admins.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    /**
     * Approve therapist
     */
    @PutMapping("/approve/{id}")
    public ResponseEntity<Void> approveTherapist(@PathVariable Integer id) {
        adminService.approveTherapist(id);
        return ResponseEntity.ok().build();
    }
}
