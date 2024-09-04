package com.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends ApiResponse<String> {
    private String token;
    private Boolean isLoggedIn;
    private String email;
}
