package com.argus.mytodo.entities.mappers;

import com.argus.mytodo.entities.Admin;
import com.argus.mytodo.entities.Client;
import com.argus.mytodo.entities.SuperAdmin;
import com.argus.mytodo.entities.User;
import com.argus.mytodo.entities.dtos.AdminDto;
import com.argus.mytodo.entities.dtos.ClientDto;
import com.argus.mytodo.entities.dtos.SuperadminDto;
import com.argus.mytodo.entities.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapFromRest(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) user.setId(userDto.getId());
        if (userDto.getUuid() != null) user.setUuid(userDto.getUuid());
        if (userDto.getFirstname() != null) user.setFirstname(userDto.getFirstname());
        if (userDto.getLastname() != null) user.setLastname(userDto.getLastname());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null) user.setPassword(userDto.getPassword());
        if (userDto.getPicture() != null) user.setPicture(userDto.getPicture());
        if (userDto.getRole() != null) user.setRole(userDto.getRole());
        return user;
    }

    public SuperAdmin mapFromRest(SuperadminDto superadminDto) {
        SuperAdmin superAdmin = new SuperAdmin();
        if (superadminDto.getId() != null) superadminDto.setId(superadminDto.getId());
        if (superadminDto.getUuid() != null) superadminDto.setUuid(superadminDto.getUuid());
        if (superadminDto.getFirstname() != null) superadminDto.setFirstname(superadminDto.getFirstname());
        if (superadminDto.getLastname() != null) superadminDto.setLastname(superadminDto.getLastname());
        if (superadminDto.getEmail() != null) superadminDto.setEmail(superadminDto.getEmail());
        if (superadminDto.getPassword() != null) superadminDto.setPassword(superadminDto.getPassword());
        if (superadminDto.getPicture() != null) superadminDto.setPicture(superadminDto.getPicture());
        if (superadminDto.getRole() != null) superadminDto.setRole(superadminDto.getRole());
        return superAdmin;
    }
    public Admin mapFromRest(AdminDto adminDto) {
        Admin admin  = new Admin();
        if (adminDto.getId() != null) admin.setId(adminDto.getId());
        if (adminDto.getUuid() != null) admin.setUuid(adminDto.getUuid());
        if (adminDto.getFirstname() != null) admin.setFirstname(adminDto.getFirstname());
        if (adminDto.getLastname() != null) admin.setLastname(adminDto.getLastname());
        if (adminDto.getEmail() != null) admin.setEmail(adminDto.getEmail());
        if (adminDto.getPassword() != null) admin.setPassword(adminDto.getPassword());
        if (adminDto.getPicture() != null) admin.setPicture(adminDto.getPicture());
        if (adminDto.getRole() != null) admin.setRole(adminDto.getRole());


        return admin;
    }
    public Client mapFromRest(ClientDto clientDto) {
        Client client = new Client();
        if (clientDto.getId() != null) client.setId(clientDto.getId());
        if (clientDto.getUuid() != null) client.setUuid(clientDto.getUuid());
        if (clientDto.getFirstname() != null) client.setFirstname(clientDto.getFirstname());
        if (clientDto.getLastname() != null) client.setLastname(clientDto.getLastname());
        if (clientDto.getEmail() != null) client.setEmail(clientDto.getEmail());
        if (clientDto.getPassword() != null) client.setPassword(clientDto.getPassword());
        if (clientDto.getPicture() != null) client.setPicture(clientDto.getPicture());
        if (clientDto.getRole() != null) client.setRole(clientDto.getRole());
        if (clientDto.getUuidAdmin() != null) client.setUuidAdmin(clientDto.getUuidAdmin());
        return client;
    }

    public SuperAdmin userToSuperadmin(User user) {
        SuperAdmin superAdmin = new SuperAdmin();
        if (user.getId() != null) superAdmin.setId(user.getId());
        if (user.getUuid() != null) superAdmin.setUuid(user.getUuid());
        if (user.getFirstname() != null) superAdmin.setFirstname(user.getFirstname());
        if (user.getLastname() != null) superAdmin.setLastname(user.getLastname());
        if (user.getEmail() != null) superAdmin.setEmail(user.getEmail());
        if (user.getPassword() != null) superAdmin.setPassword(user.getPassword());
        if (user.getPicture() != null) superAdmin.setPicture(user.getPicture());
        if (user.getRole() != null) superAdmin.setRole(user.getRole());
        return superAdmin;
    }
    public Admin userToAdmin(User user) {
        Admin admin  = new Admin();
        if (user.getId() != null) admin.setId(user.getId());
        if (user.getUuid() != null) admin.setUuid(user.getUuid());
        if (user.getFirstname() != null) admin.setFirstname(user.getFirstname());
        if (user.getLastname() != null) admin.setLastname(user.getLastname());
        if (user.getEmail() != null) admin.setEmail(user.getEmail());
        if (user.getPassword() != null) admin.setPassword(user.getPassword());
        if (user.getPicture() != null) admin.setPicture(user.getPicture());
        if (user.getRole() != null) admin.setRole(user.getRole());
        return admin;
    }
    public Client userToClient(User user) {
        Client client = new Client();
        if (user.getId() != null) client.setId(user.getId());
        if (user.getUuid() != null) client.setUuid(user.getUuid());
        if (user.getFirstname() != null) client.setFirstname(user.getFirstname());
        if (user.getLastname() != null) client.setLastname(user.getLastname());
        if (user.getEmail() != null) client.setEmail(user.getEmail());
        if (user.getPassword() != null) client.setPassword(user.getPassword());
        if (user.getPicture() != null) client.setPicture(user.getPicture());
        if (user.getRole() != null) client.setRole(user.getRole());
        return client;
    }

    public UserDto mapToRest(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUuid(user.getUuid());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPicture(user.getPicture());
        userDto.setRole(user.getRole());
        userDto.setCreationDate(user.getCreationDate());
        userDto.setLastUpdate(user.getLastUpdate());
        return userDto;
    }
    public SuperadminDto mapToRest(SuperAdmin superAdmin) {
        SuperadminDto superadminDto = new SuperadminDto();
        superadminDto.setId(superAdmin.getId());
        superadminDto.setUuid(superAdmin.getUuid());
        superadminDto.setFirstname(superAdmin.getFirstname());
        superadminDto.setLastname(superAdmin.getLastname());
        superadminDto.setEmail(superAdmin.getEmail());
        superadminDto.setPassword(superAdmin.getPassword());
        superadminDto.setPicture(superAdmin.getPicture());
        superadminDto.setRole(superAdmin.getRole());
        superadminDto.setCreationDate(superAdmin.getCreationDate());
        superadminDto.setLastUpdate(superAdmin.getLastUpdate());
        return superadminDto;
    }
    public AdminDto mapToRest(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setUuid(admin.getUuid());
        adminDto.setFirstname(admin.getFirstname());
        adminDto.setLastname(admin.getLastname());
        adminDto.setEmail(admin.getEmail());
        adminDto.setPassword(admin.getPassword());
        adminDto.setPicture(admin.getPicture());
        adminDto.setRole(admin.getRole());
        adminDto.setCreationDate(admin.getCreationDate());
        adminDto.setLastUpdate(admin.getLastUpdate());
        return adminDto;
    }
    public ClientDto mapToRest(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setUuid(client.getUuid());
        clientDto.setFirstname(client.getFirstname());
        clientDto.setLastname(client.getLastname());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setPicture(client.getPicture());
        clientDto.setRole(client.getRole());
        clientDto.setUuidAdmin(client.getUuidAdmin());
        clientDto.setCreationDate(client.getCreationDate());
        clientDto.setLastUpdate(client.getLastUpdate());
        return clientDto;
    }
    public User update(User user, UserDto userDto) {
        if (userDto.getFirstname() != null) user.setFirstname(userDto.getFirstname());
        if (userDto.getLastname() != null) user.setLastname(userDto.getLastname());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null) user.setPassword(userDto.getPassword());
        if (userDto.getPicture() != null) user.setPicture(userDto.getPicture());
        if (userDto.getRole() != null) user.setRole(userDto.getRole());
        return user;
    }
}