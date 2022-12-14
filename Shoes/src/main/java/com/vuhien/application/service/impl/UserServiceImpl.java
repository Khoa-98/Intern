package com.vuhien.application.service.impl;

import com.vuhien.application.entity.User;
import com.vuhien.application.exception.BadRequestException;
import com.vuhien.application.model.dto.UserDTO;
import com.vuhien.application.model.mapper.UserMapper;
import com.vuhien.application.model.request.ChangePasswordRequest;
import com.vuhien.application.model.request.CreateUserRequest;
import com.vuhien.application.model.request.UpdateProfileRequest;
import com.vuhien.application.repository.UserRepository;
import com.vuhien.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.vuhien.application.utils.LoggerUtil;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import static com.vuhien.application.config.Contant.LIMIT_USER;

@Log4j2
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getListUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(UserMapper.toUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public Page<User> adminListUserPages(String fullName, String phone, String email, Integer page) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, LIMIT_USER, Sort.by("created_at").descending());
        return userRepository.adminListUserPages(fullName, phone, email, pageable);
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        User user = userRepository.findByEmail(createUserRequest.getEmail());
        if (user != null) {
            LoggerUtil.loggerLogin.error("email" + user.getEmail() + "is existed");
            throw new BadRequestException("Email ???? t???n t???i trong h??? th???ng. Vui l??ng s??? d???ng email kh??c!");
        }
        user = UserMapper.toUser(createUserRequest);
        userRepository.save(user);
        LoggerUtil.loggerLogin.info("save user " + user.getEmail() + " success");
        return user;
    }

    @Override
    public void changePassword(User user, ChangePasswordRequest changePasswordRequest) {
        // Ki???m tra m???t kh???u
        if (!BCrypt.checkpw(changePasswordRequest.getOldPassword(), user.getPassword())) {

            throw new BadRequestException("M???t kh???u c?? kh??ng ch??nh x??c");
        }

        String hash = BCrypt.hashpw(changePasswordRequest.getNewPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        userRepository.save(user);

    }

    @Override
    public User updateProfile(User user, UpdateProfileRequest updateProfileRequest) {
        LoggerUtil.loggerLogin.info("update profile : " + user.getFullName());
        user.setFullName(updateProfileRequest.getFullName());
        user.setPhone(updateProfileRequest.getPhone());
        user.setAddress(updateProfileRequest.getAddress());
        LoggerUtil.loggerLogin.info("update success ");
        return userRepository.save(user);
    }
}
