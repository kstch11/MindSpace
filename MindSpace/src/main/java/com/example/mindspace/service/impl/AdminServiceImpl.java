package com.example.mindspace.service.impl;

import com.example.mindspace.repository.AdminRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {
    private final AdminRepository adminRepository;
    private final TherapistRepository therapistRepository;

    public void createAdmin(Admin admin) throws EntityNotFoundException {
        if (admin != null) {
            adminRepository.save(admin);
        } else {
            throw new EntityNotFoundException("nelzya");
        }
    }

    public void approveTherapist(Integer therapistId) {
        var therapist = therapistRepository.findById(therapistId)
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));

        therapist.setApproved(true);
        therapistRepository.save(therapist);
    }
}
