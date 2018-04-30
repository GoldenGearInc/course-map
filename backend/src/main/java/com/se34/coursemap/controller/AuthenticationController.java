package com.se34.coursemap.controller;

import com.se34.coursemap.entity.Role;
import com.se34.coursemap.entity.Student;
import com.se34.coursemap.security.model.JwtAuthenticationRequest;
import com.se34.coursemap.security.model.JwtAuthenticationResponse;
import com.se34.coursemap.security.JwtUser;
import com.se34.coursemap.security.model.UserRegistrationRequest;
import com.se34.coursemap.service.StudentService;
import com.se34.coursemap.utill.AuthenticationException;
import com.se34.coursemap.utill.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, userDetails.getAuthorities().toString());
        final String token = jwtTokenUtil.generateToken(userDetails);


        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationRequest registrationRequest) {

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, registrationRequest.toString());
        if(!registrationRequest.getPassword().equals(registrationRequest.getRepeatPassword()))
            return ResponseEntity.badRequest().body("Password and repeat password does not match");
        /*if(studentService.findByLogin(registrationRequest.getUsername())!=null)
            return ResponseEntity.badRequest().body("This username already taken");
*/
        Student newUser = new Student();
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setLogin(registrationRequest.getUsername());
        newUser.setFirstName(registrationRequest.getFirstName());
        newUser.setLastName(registrationRequest.getLastName());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setEnabled(true);
        newUser.setLastPasswordResetDate(new Date());
        //Role role = new Role();
        //role.setRole("student");
        //newUser.setRole(role);

        studentService.addStudent(newUser);
        return new ResponseEntity<Student>(newUser,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }



}
