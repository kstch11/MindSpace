package com.example.mindspace.service.impl;

import com.example.mindspace.api.AdminResponse;
import com.example.mindspace.api.UserResponse;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.repository.UserRepository;
import com.example.mindspace.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final ClientServiceImpl clientService;
    private final TherapistServiceImpl therapistService;

    /**
     * Retrieves the current user's details based on the user type (client, therapist, admin).
     * It uses the provided UserPrincipal to identify the user and fetches the corresponding details.
     *
     * @param userPrincipal The principal object containing the user's identification information.
     * @return UserResponse object containing details of the current user.
     * @throws EntityNotFoundException if the user is not found in the repository.
     */
    public UserResponse getCurrentUser(UserPrincipal userPrincipal) {
        var id = userPrincipal.getId();
        var user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        switch (user.getUserType()) {
            case CLIENT -> {
                return clientService.getClientDetails(id);
            }
            case THERAPIST -> {
                return therapistService.getTherapistDetails(id);
            }
            default -> {
                return new AdminResponse(id, user.getName(), true, true);
            }
        }
    }
}
