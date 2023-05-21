package com.example.mindspace.service.impl;

import com.example.mindspace.dao.AdminRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Admin;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final TherapistRepository therapistRepository;

    @Override
    public void createAdmin(Admin admin) throws EntityNotFoundException {
        if (admin != null) {
            adminRepository.save(admin);
        } else {
            throw new EntityNotFoundException("nelzya");
        }
    }

    @Override
    public void approveTherapist(Therapist therapist) {

    }
}
