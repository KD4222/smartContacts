package com.smartContacts.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.lang.reflect.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Username is required.")
    @Size(min = 3, message = "Min 3 char required.")
    private String name;
    @Email(message ="Invalid Email Address.")
    @NotBlank(message ="Email is required.")
    private String email;
    @NotBlank(message ="Password can not be blank.")
    @Size(min = 6, message = "Min 6 char required.")
    private String password;
    @NotBlank(message = "About is required.")
    private String about;
    @Size(min = 8,max = 12,message = "Invalid phone number.")
    private String phoneNumber;
}
