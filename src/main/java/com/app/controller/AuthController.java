package com.app.controller;

import com.app.entity.User;
import com.app.payload.JWTTokenDto;
import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import com.app.service.JWTService;
import com.app.service.OTPService;
import com.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserRepository userRepository;
    private UserService userService;
    private OTPService otpservice;
    private JWTService jwtService;

    public AuthController(UserRepository userRepository, UserService userService, OTPService otpservice, JWTService jwtService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.otpservice = otpservice;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")

    public ResponseEntity<String> createUser(@RequestBody User user) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username exists", INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email exists", INTERNAL_SERVER_ERROR);
        }
        //String encodedPassword =  passwordEncoder.encode(user.getPassword());
        //user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return new ResponseEntity<>("user created", HttpStatus.CREATED);

    }

    @PostMapping("/content-manager-signup")

    public ResponseEntity<String> createContentManagerAccount(@RequestBody User user) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username exists", INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email exists", INTERNAL_SERVER_ERROR);
        }
        //String encodedPassword =  passwordEncoder.encode(user.getPassword());
        //user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENTMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("content manager created", HttpStatus.CREATED);

    }

    @PostMapping("/blog-manager-signup")

    public ResponseEntity<String> createBlogManagerAccountUser(@RequestBody User user) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            return new ResponseEntity<>("username exists", INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if (opEmail.isPresent()) {
            return new ResponseEntity<>("email exists", INTERNAL_SERVER_ERROR);
        }
        //String encodedPassword =  passwordEncoder.encode(user.getPassword());
        //user.setPassword(encodedPassword);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOGMANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("user created", HttpStatus.CREATED);

    }

    @PostMapping("/usersign")
    public ResponseEntity<?> userSignIn(
            @RequestBody LoginDto dto


    ) {
        String jwtToken = userService.verifyLogin(dto);
        if (jwtToken != null) {
            JWTTokenDto tokenDto = new JWTTokenDto();
            tokenDto.setToken(jwtToken);
            tokenDto.setTokenType("JWT");

            return new ResponseEntity<>(tokenDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid token", INTERNAL_SERVER_ERROR);
    }

    //     @PostMapping("/message")
//    public String getMessage(){
//        return "Hello";
    @PostMapping("/login-otp")
    public String generateOtp(
            @RequestParam String mobile
    ) {

        Optional<User> opUser = userRepository.findByMobile(mobile);
        if (opUser.isPresent()) {

            String otp = otpservice.generateOTP(mobile);
            return otp + " - " + mobile;

        }
        return "User not found";
    }
    @PostMapping("/validate-otp")
    public String validateOtp(
            @RequestParam String mobile,
            @RequestParam String otp

    ) {
       boolean status =  otpservice.validateOTP(mobile, otp);
       if(status){
           //generate JWT Token
           Optional<User> opUser = userRepository.findByMobile(mobile);
           if(opUser.isPresent()){
               String jwtToken = jwtService.generateToken(opUser.get().getUsername());
               return jwtToken;
           }
       }
        return status? "OTP validate successfully" : "Invalid OTP";
    }
}
