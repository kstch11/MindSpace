package com.example.mindspace.service.interfaces;

import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Admin;
import com.example.mindspace.model.Therapist;

public interface AdminService {

    public void createAdmin(Admin admin) throws EntityNotFoundException;

    public void approveTherapist(Therapist therapist);

}
