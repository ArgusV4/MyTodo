package com.argus.mytodo.controllers;

import com.argus.mytodo.entities.Client;
import com.argus.mytodo.entities.dtos.ClientDto;
import com.argus.mytodo.jwt.AuthRequest;
import com.argus.mytodo.entities.User;
import com.argus.mytodo.entities.dtos.UserDto;
import com.argus.mytodo.entities.mappers.UserMapper;
import com.argus.mytodo.jwt.AuthResponse;
import com.argus.mytodo.services.FilesStorageService;
import com.argus.mytodo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.argus.mytodo.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final FilesStorageService storageService;
    private final UserMapper userMapper;

    public UserController(UserService userService, FilesStorageService storageService, UserMapper userMapper) {
        this.userService = userService;
        this.storageService = storageService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> response = this.userService.getAllUsers();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto response = this.userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestParam("subject") String subject, @RequestParam(value = "file", required=false) MultipartFile file) throws JsonProcessingException {
        UserDto request = new ObjectMapper().readValue(subject, UserDto.class);
        User response = this.userService.createUser(request, file);
        return ResponseEntity.ok(this.userMapper.mapToRest(response));
    }
    @PostMapping("/createClientByAdmin")
    public ResponseEntity<ClientDto> createClientByAdmin(@RequestParam("subject") String subject, @RequestParam(value = "file", required=false) MultipartFile file, HttpServletRequest requestHeader) throws JsonProcessingException {
        String token = requestHeader.getHeader("Authorization").substring(7);
        ClientDto request = new ObjectMapper().readValue(subject, ClientDto.class);
        Client response = this.userService.createClientByAdmin(request, file, token);
        return ResponseEntity.ok(this.userMapper.mapToRest(response));
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestParam("subject") String subject, @RequestParam(value = "file", required=false) MultipartFile file ) throws JsonProcessingException {
        UserDto request = new ObjectMapper().readValue(subject, UserDto.class);
        return ResponseEntity.ok(this.userMapper.mapToRest(this.userService.updateUser(id, request, file)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id ) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok("Success");
    }
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        Resource file = storageService.load(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/generateToken")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return this.userService.generateToken(authRequest);
    }

    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }

    @PostMapping("create-new-user")
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid UserDto userDto){
        User user = this.userMapper.mapFromRest(userDto);
        return ResponseEntity.ok(this.userMapper.mapToRest(this.userService.createNewUser(user)));
    }

}

