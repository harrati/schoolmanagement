package ma.harrati.school.unit.ws.facade.admin.school;

import ma.harrati.school.bean.core.school.Principal;
import ma.harrati.school.service.impl.admin.school.PrincipalAdminServiceImpl;
import ma.harrati.school.ws.facade.admin.school.PrincipalRestAdmin;
import ma.harrati.school.ws.converter.school.PrincipalConverter;
import ma.harrati.school.ws.dto.school.PrincipalDto;
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
public class PrincipalRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private PrincipalAdminServiceImpl service;
    @Mock
    private PrincipalConverter converter;

    @InjectMocks
    private PrincipalRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllPrincipalTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<PrincipalDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<PrincipalDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSavePrincipalTest() throws Exception {
        // Mock data
        PrincipalDto requestDto = new PrincipalDto();
        Principal entity = new Principal();
        Principal saved = new Principal();
        PrincipalDto savedDto = new PrincipalDto();

        // Mock the converter to return the principal object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved principal DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<PrincipalDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        PrincipalDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved principal DTO
        assertEquals(savedDto.getFirstName(), responseBody.getFirstName());
        assertEquals(savedDto.getLastName(), responseBody.getLastName());
        assertEquals(savedDto.getPhoneNumber(), responseBody.getPhoneNumber());
        assertEquals(savedDto.getPasswordChanged(), responseBody.getPasswordChanged());
        assertEquals(savedDto.getAccountNonLocked(), responseBody.getAccountNonLocked());
        assertEquals(savedDto.getPassword(), responseBody.getPassword());
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getEnabled(), responseBody.getEnabled());
        assertEquals(savedDto.getCredentialsNonExpired(), responseBody.getCredentialsNonExpired());
        assertEquals(savedDto.getAccountNonExpired(), responseBody.getAccountNonExpired());
        assertEquals(savedDto.getUsername(), responseBody.getUsername());
    }

}
