package com.davcode.clock.controllers;

import com.davcode.clock.mappers.dto.DtoMapper;
import com.davcode.clock.mappers.dto.EmployeeResponse;
import com.davcode.clock.mappers.json.EmployeeJson;
import com.davcode.clock.services.CompanyServiceTest;
import com.davcode.clock.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private EmployeeJson employeeJson = createEmployeeJson();
    private List<EmployeeResponse> employeeResponses = createEmployeesList();


    @Test
    public void testAddEmployee() throws Exception {
        mockMvc.perform(post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeJson))
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetAllFromCompany() throws Exception {
        when(employeeService.getAllEmployeesFromCompany(1L)).thenReturn(employeeResponses);
        mockMvc.perform(
                        get("/api/v1/employee/getAll/1")
                                .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(employeeResponses.size())));

        verify(employeeService, times(1)).getAllEmployeesFromCompany(anyLong());
    }

    @Test
    public void testGetEmployee() throws Exception {
        when(employeeService.getEmployee(1L,1L)).thenReturn(employeeResponses.get(0));
        mockMvc.perform(
                        get("/api/v1/employee/1")
                                .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(employeeResponses.get(0).getName())));

        verify(employeeService, times(1)).getEmployee(anyLong(),anyLong());
    }

    private EmployeeJson createEmployeeJson() {
        return EmployeeJson.builder()
                .firstName("test")
                .lastName("test")
                .employeeId(1L)
                .monthlySalary(500L)
                .hourlySalary(15L)
                .contractType("test")
                .positionId(1)
                .companyId(1L)
                .email("test@test.com")
                .internalEmployeeId(19L)
                .assignedStartTime("08:00")
                .assignedEndTime("23:00")
                .build();

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private List<EmployeeResponse> createEmployeesList() {
        List<EmployeeResponse> retval = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            retval.add(
                    DtoMapper.employeeToDto(
                            DtoMapper.addCompanyToExistingEmployee(
                                    DtoMapper.employeeJsonToObj(
                                            createEmployeeJson()
                                    ),
                                    CompanyServiceTest.createTestCompany()
                            )
                    )
            );

        }
        return retval;
    }


}
