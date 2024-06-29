package ru.gazprombank.app.bot;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserClientTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserClient userClient;

    @Test
    public void testRegisterUser_SuccessfulResponse() {
        MockitoAnnotations.initMocks(this);
        String successfulResponse = "{ \"userId\": 123 }";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(successfulResponse, HttpStatus.OK));

        String actual = userClient.register(1684L, "joeblack");

        assertEquals(UserClient.REGISTER_SUCCESS_MESSAGE, actual);
    }

    @Test
    public void testRegisterUser_ErrorResponse() {
        MockitoAnnotations.initMocks(this);
        String errorMessage = "error message";
        String errorResponse = "{ \"error\": \"" + errorMessage + "\" }";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(errorResponse, HttpStatus.OK));

        String actual = userClient.register(1684L, "joeblack");

        assertEquals(errorMessage, actual);
    }

    @Test
    public void testRegisterUser_InternalServerError() {
        MockitoAnnotations.initMocks(this);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

        String actual = userClient.register(1684L, "joeblack");

        assertEquals(UserClient.DEFAULT_ERROR_MESSAGE, actual);
    }
}
