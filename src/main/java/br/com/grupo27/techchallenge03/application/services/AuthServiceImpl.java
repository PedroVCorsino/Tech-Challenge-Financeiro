package br.com.grupo27.techchallenge03.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.grupo27.techchallenge03.application.dto.UserDTO;
import br.com.grupo27.techchallenge03.domain.interfaces.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "https://0ge70f466e.execute-api.us-east-1.amazonaws.com/Prod/";
    
    @Override
    public ResponseEntity<String> authenticate(UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(userDTO, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/auth", requestEntity, String.class);
        return response;
    }
}
