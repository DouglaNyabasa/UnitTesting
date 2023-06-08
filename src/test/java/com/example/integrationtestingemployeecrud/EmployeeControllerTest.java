package com.example.integrationtestingemployeecrud;

import com.example.integrationtestingemployeecrud.controller.EmployeeController;
import com.example.integrationtestingemployeecrud.model.Employee;
import com.example.integrationtestingemployeecrud.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    Employee employee1 = new Employee(1L,"Douglas","nyabasa","01/20/1999","0784320423","testemail@gmail.com");
    Employee employee2 = new Employee(2L,"Goku","uzumaki","01/20/1996","0784320423","narutoemail@gmail.com");
    Employee employee3 = new Employee(3L,"ichigo","kurosaki","01/20/1995","0784320423","ichigomail@gmail.com");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getAllEmployees_success() throws Exception{
        List<Employee> employees = new ArrayList<>(Arrays.asList(employee1,employee2,employee3));
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[2].firstname",is("ichigo")));
    }

    @Test
    public void getEmployeeById_success() throws Exception{
        Mockito.when(employeeRepository.findById(employee1.getId())).thenReturn(java.util.Optional.of(employee1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstname",is("Douglas")));


    }
    @Test
    public void deleteEmployeeById_success() throws Exception{
        Mockito.when(employeeRepository.findById(employee2.getId())).thenReturn(java.util.Optional.ofNullable(employee2));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/delete/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void updateEmployee_success()throws Exception{
        Employee updatedEmployee = Employee.builder()
                .id(1L)
                .firstname("updated employee name")
                .lastName("updated surname ")
                .email("updated@gmail.com")
                .phoneNumber("+263784320423")
                .dateOfBirth("07/06/2023")
                .build();

        Mockito.when(employeeRepository.findById(employee1.getId())).thenReturn( java.util.Optional.ofNullable(employee1));
        Mockito.when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);

        String updatedcontent = objectWriter.writeValueAsString(updatedEmployee);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/employee/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedcontent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstname",is("updated employee name")));

    }
    @Test
    public void addEmployee_success()throws Exception{
        Employee employee = Employee.builder()
                .id(4L)
                .firstname("Goku")
                .lastName("kakarote")
                .dateOfBirth("1998")
                .phoneNumber("07855555")
                .email("goku@gmail.com")
                .build();

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

        String content = objectWriter.writeValueAsString(employee);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstname",is("Goku")));
    }
}
