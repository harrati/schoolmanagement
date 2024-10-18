package ma.harrati.school.unit.ws.facade.admin.school;

import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.service.impl.admin.school.SchoolAdminServiceImpl;
import ma.harrati.school.ws.facade.admin.school.SchoolRestAdmin;
import ma.harrati.school.ws.converter.school.SchoolConverter;
import ma.harrati.school.ws.dto.school.SchoolDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SchoolRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private SchoolAdminServiceImpl service;
    @Mock
    private SchoolConverter converter;

    @InjectMocks
    private SchoolRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSchoolTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SchoolDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SchoolDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSchoolTest() throws Exception {
        // Mock data
        SchoolDto requestDto = new SchoolDto();
        School entity = new School();
        School saved = new School();
        SchoolDto savedDto = new SchoolDto();

        // Mock the converter to return the school object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved school DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SchoolDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SchoolDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved school DTO
        assertEquals(savedDto.getName(), responseBody.getName());
        assertEquals(savedDto.getAddress(), responseBody.getAddress());
        assertEquals(savedDto.getPhoneNumber(), responseBody.getPhoneNumber());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
