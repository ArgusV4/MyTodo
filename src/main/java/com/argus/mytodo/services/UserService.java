package com.argus.mytodo.services;

import com.argus.mytodo.Enums.RoleENUM;
import com.argus.mytodo.entities.Admin;
import com.argus.mytodo.entities.Client;
import com.argus.mytodo.entities.SuperAdmin;
import com.argus.mytodo.entities.User;
import com.argus.mytodo.entities.dtos.UserDto;
import com.argus.mytodo.entities.mappers.UserMapper;
import com.argus.mytodo.exceptionhandler.AlreadyExistsExeption;
import com.argus.mytodo.exceptionhandler.NotFoundException;
import com.argus.mytodo.repositories.AdminRepository;
import com.argus.mytodo.repositories.ClientRepository;
import com.argus.mytodo.repositories.SuperadminRepository;
import com.argus.mytodo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class UserService {
    private final FilesStorageService storageService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SuperadminRepository superadminRepository;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public UserService(FilesStorageService storageService, UserMapper userMapper, UserRepository userRepository, SuperadminRepository superadminRepository, AdminRepository adminRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.storageService = storageService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.superadminRepository = superadminRepository;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserDto> getAllUsers(){
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        users.forEach(user -> usersDto.add(this.userMapper.mapToRest(user)));
        return usersDto;
    }
    public UserDto getUserById(long id){
        UserDto userDto;
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()){
            userDto = this.userMapper.mapToRest(user.get());
        } else
            throw new NotFoundException("user", id);
        return userDto;
    }

    public User createUser(UserDto userDto, MultipartFile file){
        userRepository.findByEmail(userDto.getEmail()).ifPresent(value -> {
            throw new AlreadyExistsExeption("email", value.getEmail());
        });
        if(file != null) userDto.setPicture(file.getOriginalFilename());
        User user = this.userMapper.mapFromRest(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPicture(this.storageService.save(file));
        switch (user.getRole()){
            case SUPERADMIN : {
                SuperAdmin superAdmin = this.userMapper.userToSuperadmin(user);
                this.superadminRepository.save(superAdmin);
                return superAdmin;
            }
            case ADMIN : {
                Admin admin = this.userMapper.userToAdmin(user);
                this.adminRepository.save(admin);
                return admin;
            }
            case CLIENT : {
                Client client = this.userMapper.userToClient(user);
                this.clientRepository.save(client);
                return client;
            }
        }
        return user;
    }
    public User updateUser(Long id , UserDto request, MultipartFile file){
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("user", id));
        if(file != null){
            request.setPicture(this.storageService.save(file));
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        user = this.userMapper.update(user, request);
        this.userRepository.save(user);
        return user;
    }
    public void deleteUser(Long id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("user", id));
        switch (user.getRole()){
            case SUPERADMIN : {
                this.superadminRepository.deleteById(user.getId());
                break;
            }
            case ADMIN : {
                this.adminRepository.deleteById(user.getId());
                break;
            }
            case CLIENT : {
                this.clientRepository.deleteById(user.getId());
                break;
            }
        }
        this.userRepository.deleteById(id);
    }

    public void createSuperAdminIfNotExists() {
        String rawPassword = "system1199";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        String userSQL = "INSERT INTO mytodo.\"user\" (id, uuid, creationDate, lastUpdate, firstname, lastname, password, email, role, picture) " +
                "SELECT 1000, '214ee728-9102-4486-8519-5845629b934e', NOW(), NOW(), 'Amir', 'Boussaffara', ?, 'argus@system.com', 'SUPERADMIN', 'admin.jpg' " +
                "WHERE NOT EXISTS (SELECT 1 FROM mytodo.\"user\" WHERE id = 1000 OR email = 'argus@system.com')";
        String superAdminSQL = "INSERT INTO mytodo.\"superadmin\" (id) " +
                "SELECT 1000 "+
                "WHERE NOT EXISTS (SELECT 1 FROM mytodo.\"superadmin\" WHERE id = 1000)";

        jdbcTemplate.update(userSQL, encodedPassword);
        jdbcTemplate.update(superAdminSQL);
    }

}
