package com.davcode.clock.services;

import com.davcode.clock.enums.UserStatus;
import com.davcode.clock.exceptions.Exceptions;
import com.davcode.clock.mappers.RequestJson;
import com.davcode.clock.mappers.RequestMapper;
import com.davcode.clock.mappers.dto.RequestDTO;
import com.davcode.clock.models.Employee;
import com.davcode.clock.models.User;
import com.davcode.clock.repositories.UserRepository;
import com.davcode.clock.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RequestMapper requestMapper;
    private CompanyService companyService;
    private EmployeeService employeeService;

    @Autowired
    public UserService(UserRepository userRepository, RequestMapper requestMapper, CompanyService companyService, EmployeeService employeeService) {
        this.userRepository = userRepository;
        this.requestMapper = requestMapper;
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    public void addUser(User user){
        user.setCreationDate(LocalDate.now());
        userRepository.save(user);
    }

    public void addUserAndEmployee(RequestJson requestJson){
        Employee employee = new Employee();
        employee.setEmail(requestJson.getEmail());
        employee.setInternalEmployeeId(requestJson.getInternalEmployeeId());
        employee.setAssignedStartTime(Utils.ltparse(requestJson.getAssignedStartTime()));
        employee.setAssignedEndTime(Utils.ltparse(requestJson.getAssignedEndTime()));
        employee.setGroupId(requestJson.getGroupId());
        employee.setFirstName(requestJson.getFirstName());
        employee.setLastName(requestJson.getLastName());
        employee.setOrganizationId(requestJson.getOrganizationId());
        employee.setPositionId(requestJson.getPositionId());
        employee.setCompany(companyService.getById(requestJson.getCompanyId()));

        employeeService.addEmployee(employee);

        User user = new User();
        user.setUserName(requestJson.getUserName());
        user.setCreationDate(LocalDate.now());
        user.setStatus(requestJson.getStatus());
        user.setActive(requestJson.isActive());
        user.setPassword(requestJson.getPassword());
        user.setRole(requestJson.getRole());
        user.setSuspensionDate(Utils.ldparse(requestJson.getSuspensionDate()));
        user.setEmailVerified(requestJson.isEmailVerified());
        user.setChangePasswordOnNextLogin(requestJson.isChangePasswordOnNextLogin());
        user.setEmployee(employee);

        addUser(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void changeUserStatus(Long id, char newStatus){
        Optional<User> userInDB = userRepository.findById(id);
        if (userInDB.isPresent()){
            if(Arrays.stream(UserStatus.values()).anyMatch(userStatus -> userStatus.getValue() == newStatus)){
                User user = userInDB.get();
                user.setStatus(newStatus);
                userRepository.save(user);
            }
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
        if (userInDB.isPresent()){
            User user = userInDB.get();
            user.setEmailVerified(true);
            userRepository.save(user);
        }
    }

    public User getByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }

    public void invalidateUser(Long id){
        Optional<User> userInDB = userRepository.findById(id);
        if (userInDB.isPresent()){
            User user = userInDB.get();
            user.setActive(false);
            user.setStatus(UserStatus.I.getValue());
            user.setSuspensionDate(LocalDate.now());
        }
    }

    public void updatePassword(Long id, String password){
        Optional<User> userInDB = userRepository.findById(id);
        if (userInDB.isPresent()){
            User user = userInDB.get();
            user.setPassword(password);
            userRepository.save(user);
        }
    }

    public RequestDTO getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new Exceptions.UserNotFoundException("No user with id " + id);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUser(user.get());
        requestDTO.setEmployee(user.get().getEmployee());
        return requestDTO;
    }

    public User getUserByIdInternal(Long id){
        return userRepository.findById(id).get();
    }

}
