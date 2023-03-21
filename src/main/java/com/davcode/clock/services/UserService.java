package com.davcode.clock.services;

import com.davcode.clock.enums.UserStatus;
import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.mappers.RequestJson;
import com.davcode.clock.mappers.RequestMapper;
import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.mappers.dto.RequestDTO;
import com.davcode.clock.mappers.dto.ResponseDTO;
import com.davcode.clock.mappers.dto.UserResponse;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RequestMapper requestMapper;
    private CompanyService companyService;
    private EmployeeService employeeService;
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RequestMapper requestMapper, CompanyService companyService, EmployeeService employeeService, UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.requestMapper = requestMapper;
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user){
        user.setCreationDate(LocalDate.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void addUserAndEmployee(RequestJson requestJson){
        RequestDTO requestDTO = requestMapper.toRequestDto(requestJson);
        User user = requestDTO.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Employee employee = requestDTO.getEmployee();
        employeeService.addEmployee(employee);
        user.setEmployee(employee);
        user.setAutoScheduleAllowed(employee.getCompany().isAllowAutoSchedule());
        addUser(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void updateUserStatus(Long id, char newStatus){
        Optional<User> userInDB = userRepository.findById(id);
        if (!userInDB.isPresent())
            throw new Exceptions.UserNotFoundException("User not found");
        if(Arrays.stream(UserStatus.values()).anyMatch(userStatus -> userStatus.getValue() == newStatus)){
            User user = userInDB.get();
            user.setStatus(newStatus);
            userRepository.save(user);
        }
    }

    public void updateActiveFlag(Long id, boolean isActive){
        Optional<User> userInDB = userRepository.findById(id);
        if (userInDB.isPresent()){
            User user = userInDB.get();
            user.setActive(isActive);
            userRepository.save(user);
        }
    }

    public void updateEmailConfirmation(Long id){
        Optional<User> userInDB = userRepository.findById(id);
        if (userInDB.isPresent())
            throw new Exceptions.UserNotFoundException("No user with id " + id);
        User user = userInDB.get();
        user.setEmailVerified(true);
        userRepository.save(user);
    }

    public UserResponse getByUserName(String userName){
        return DtoMapper.UserToDto(userRepository.findByUserName(userName));
    }

    public void invalidateUser(Long id){
        Optional<User> userInDB = userRepository.findById(id);
        if (!userInDB.isPresent())
            throw new Exceptions.UserNotFoundException("No user with id " + id);
        User user = userInDB.get();
        user.setActive(false);
        user.setStatus(UserStatus.I.getValue());
        user.setSuspensionDate(LocalDate.now());


    }

    public void updatePassword(Long id, String password){
        Optional<User> userInDB = userRepository.findById(id);
        if (userInDB.isPresent())
            throw new Exceptions.UserNotFoundException("No user with id " + id);
        User user = userInDB.get();
        user.setPassword(password);
        userRepository.save(user);
    }

    /*public RequestDTO getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new Exceptions.UserNotFoundException("No user with id " + id);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUser(user.get());
        requestDTO.setEmployee(user.get().getEmployee());
        return requestDTO;
    }*/

    public UserResponse getUser(String username){
        User user = userRepository.findByUserName(username);
        UserResponse userResponse = DtoMapper.UserToDto(user);
        return userResponse;
    }

    public User getUserByIdInternal(Long id){
        return userRepository.findById(id).get();
    }

    public ResponseDTO getUserAndEmployee(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
            throw new Exceptions.UserNotFoundException("No user with id " + userId);
        return new ResponseDTO(
                DtoMapper.UserToDto(user.get()),
                DtoMapper.employeeToDto(user.get().getEmployee())
        );

    }

    List<User> getUsersFromCompany(Long companyId){
        return userRepository.findAllByEmployee_Company_Id(companyId);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

}
