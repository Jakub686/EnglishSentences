package com.jakubchyla.englishsentences.auth;

import com.jakubchyla.englishsentences.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;

    private Role role;

    private String email;
}
