package bg.softuni.marketplace.model.dto;

import bg.softuni.marketplace.model.validation.EmailValidation;
import bg.softuni.marketplace.model.validation.PasswordMatch;
import bg.softuni.marketplace.model.validation.UsernameMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@PasswordMatch(first = "password", second = "confirmPassword", message = "Passwords do not match!")
public class UserRegisterDto {

    @NotEmpty(message = "Username must not be empty!")
    @Size(min = 3, max = 20, message = "Length must be between 3 and 20 symbols!")
    @UsernameMatch
    private String username;

    @Email
    @EmailValidation
    @NotEmpty(message = "Email is required!")
    private String email;

    @NotEmpty(message = "First name must not be empty!")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 symbols!")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty!")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 symbols!")
    private String lastName;


    @NotEmpty(message = "Password must not be empty!")
    @Size(min = 3, message = "Password size must be at lest 3 symbols!")
    private String password;

    @NotEmpty(message = "Password must not be empty!")
    @Size(min = 3, message = "Password size must be at lest 3 symbols!")
    private String confirmPassword;


    private String townName;

    private String ipAddress;

    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getTownName() {
        return townName;
    }

    public UserRegisterDto setTownName(String townName) {
        this.townName = townName;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public UserRegisterDto setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }
}
