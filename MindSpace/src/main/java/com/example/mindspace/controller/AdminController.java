package com.example.mindspace.controller;

import com.example.mindspace.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Service for admins.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final Logger LOG = Logger.getLogger(AdminController.class.getName());
    private final AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    /**
     * Approves a therapist based on their ID.
     * This method allows the admin to approve the registration of a therapist.
     *
     * @param id The ID of the therapist to be approved.
     * @return ResponseEntity indicating the operation's success.
     */
    @PutMapping("/approve/{id}")
    public ResponseEntity<Void> approveTherapist(@PathVariable Integer id) {
        LOG.info("Admin is here");
        adminService.approveTherapist(id);
        return ResponseEntity.ok().build();
    }
}
