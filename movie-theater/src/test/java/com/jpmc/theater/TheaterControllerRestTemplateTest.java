package com.jpmc.theater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.vo.Movie;
import com.jpmc.theater.vo.MovieRepository;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.Duration;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
@ActiveProfiles("test")
public class TheaterControllerRestTemplateTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private MovieRepository mockRepository;

    @Before
    public void init() {
        //Book book = new Book(1L, "Book Name", "Mkyong", new BigDecimal("9.99"));
        Movie movie = new Movie(1L,"Turning Red", "Turning Red", Duration.ofMinutes(85), 11, 0);
        when(mockRepository.findById(1L)).thenReturn(Optional.of(movie));
    }

    @Test
    public void find_bookId_OK() throws JSONException {

        String expected = "[{ \"id\": 1,\"title\": \"Turning Red\",\"description\": \"Turning Red\", \"runningTime\": \"PT1H25M\", \"ticketPrice\": 8.0, \"specialCode\": 0}]";

        ResponseEntity<String> response = restTemplate.getForEntity("/movie", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());

        JSONAssert.assertEquals(expected, response.getBody(), false);

        //verify(mockRepository, times(1)).findById(1L);

    }

}

