package ru.gazprombank.app.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class UserClient {
    private String host;
    private RestTemplate restTemplate;

    @Autowired
    public UserClient(@Value("${middle_service.host}") String host, RestTemplate restTemplate) {
        super();
        this.host = host;
        this.restTemplate = restTemplate;
    }

    public String register(Long telegramUserId, String telegramUserName) {
        try {
            String url = this.host + "/users";

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("telegram_user_id", telegramUserId);
            jsonObject.put("telegram_user_name", telegramUserName);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity(jsonObject.toString(), headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            String responseBody = responseEntity.getBody();

            try {
                RegisterUserResponseBody registerUserResponseBody = objectMapper.readValue(responseBody, RegisterUserResponseBody.class);
                if (registerUserResponseBody.userId != null) {
                    return REGISTER_SUCCESS_MESSAGE;
                } else {
                    return DEFAULT_ERROR_MESSAGE;
                }
            } catch (Exception success_exception) {
                try {
                    ErrorResponseBody errorResponseBody = objectMapper.readValue(responseBody, ErrorResponseBody.class);
                    return errorResponseBody.error;
                } catch (Exception error_exception) {
                    return DEFAULT_ERROR_MESSAGE;
                }
            }
        } catch (Exception e) {
            return DEFAULT_ERROR_MESSAGE;
        }
    }

    public static String REGISTER_SUCCESS_MESSAGE = "Вы успешно зарегистрированы!";
    public static String DEFAULT_ERROR_MESSAGE = "В системе произошел сбой и мы не можем Вас зарегистрировать. Попробуйте позже - мы уже занимаемся этой проблемой";

    private ObjectMapper objectMapper = new ObjectMapper();
    public record RegisterUserResponseBody(Integer userId) {}
    public record ErrorResponseBody(String error) {}
}
