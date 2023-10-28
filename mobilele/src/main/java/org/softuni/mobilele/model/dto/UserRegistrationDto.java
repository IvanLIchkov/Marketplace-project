package org.softuni.mobilele.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.softuni.mobilele.model.validations.EmailValidation;
import org.softuni.mobilele.model.validations.PasswordMatch;

@PasswordMatch(first = "password", second = "confirmPassword", message = "Passwords doesn't match!")
public class UserRegistrationDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    @EmailValidation
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
