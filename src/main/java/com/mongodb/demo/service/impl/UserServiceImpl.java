package com.mongodb.demo.service.impl;

import com.mongodb.demo.model.dto.UserDto;
import com.mongodb.demo.model.entity.Address;
import com.mongodb.demo.model.entity.Gender;
import com.mongodb.demo.model.entity.RoleName;
import com.mongodb.demo.model.entity.User;
import com.mongodb.demo.model.mapper.UserMapper;
import com.mongodb.demo.model.request.SignUpRequest;
import com.mongodb.demo.repository.UserRepository;
import com.mongodb.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final UserMapper userMapper;

    private final MongoTemplate mongoTemplate;

    @Override
    public void addUser() {
        User user = new User();
        user.setUsername("nanda");
        user.setFullName("thaw nanda");
        user.setPassword("12345678");
        user.setGender(Gender.MALE);
        Address address = new Address();
        address.setCity("Mandalay");
        address.setCountry("Myanmar");
        address.setPostCode("007");
        user.setAddress(address);
        user.setRoles(List.of(RoleName.ROLE_USER));
        userRepository.save(user);
    }

    @Override
    public void signUpUser(SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        if (request.getGender().equals("M")) {
            user.setGender(Gender.MALE);
        } else {
            user.setGender(Gender.FEMALE);
        }
        Address address = new Address();
        address.setCity(request.getAddress().getCity());
        address.setCountry(request.getAddress().getCountry());
        address.setPostCode(request.getAddress().getPostCode());
        user.setAddress(address);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(List.of(RoleName.ROLE_USER));
        userRepository.save(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getUsersByCountry(String country) {
        Criteria criteria = Criteria.where("address.country").is(country);
        Query query = new Query(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        return users.stream().map(userMapper::toUserDto).toList();
    }

}
