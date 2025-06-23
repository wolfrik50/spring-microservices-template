package io.wulfcodes.user.client;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.wulfcodes.common.exception.GenericException;
import io.wulfcodes.common.model.data.RatingData;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class RatingClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public List<RatingData> getAllRatings(String userId) {
        URI requestUri = UriComponentsBuilder.fromUriString("http://RATING-SERVICE/api/v1/ratings")
                                      .queryParam("user_id", userId)
                                      .build()
                                      .toUri();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(APPLICATION_JSON));
        requestHeaders.setContentType(APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<String> response = restTemplate.exchange(requestUri, GET, requestEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful() || !response.hasBody())
            return Collections.emptyList();

        String responseBody = response.getBody();

        try {
            JsonNode responseJson = objectMapper.readTree(responseBody);
            JsonNode payloadJson = responseJson.path("payload");
            return objectMapper.readerForListOf(RatingData.class).readValue(payloadJson);
        } catch (IOException ex) {
            throw new GenericException("Something wrong happened while transforming payload!");
        }

    }

}
