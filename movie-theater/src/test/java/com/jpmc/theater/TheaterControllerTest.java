package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.vo.LocalDateProvider;
import com.jpmc.theater.vo.Movie;
import com.jpmc.theater.vo.Showing;
import com.jpmc.theater.vo.ShowingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(BookController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TheaterControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShowingRepository mockRepository;

    @Before
    public void init() {
        Showing showing = new Showing(2L,new Movie(1L,"Turning Red", "Turning Red", Duration.ofMinutes(85), 11, 0), 1, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(9, 0)));
        when(mockRepository.findById(2L)).thenReturn(Optional.of(showing));
    }

    @Test
    public void find_ShowingId_OK() throws Exception {

        mockMvc.perform(get("/movie"))
                //*.andDo(print())*//*
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.id", is(2)))
                //.andExpect(jsonPath("$.name", is("Book Name")))
                //.andExpect(jsonPath("$.author", is("Mkyong")))
                //.andExpect(jsonPath("$.price", is(9.99)));

        //verify(mockRepository, times(1)).findById(2L);

    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

