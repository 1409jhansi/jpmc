package com.jpmc.theater;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import com.jpmc.theater.vo.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
public class StartTheaterApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(com.jpmc.theater.StartTheaterApplication.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(ShowingRepository repository,MovieRepository movieRepository) {
        return args -> {
            repository.save(new Showing(new Movie("Turning Red", "Turning Red", Duration.ofMinutes(85), 11, 0), 1, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(9, 0))));
            repository.save(new Showing(new Movie("Spider-Man: No Way Home", "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 2, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(11, 0))));
            repository.save(new Showing(new Movie("The Batman", "The Batman", Duration.ofMinutes(95), 9, 0), 3, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(12, 50))));
            repository.save(new Showing(new Movie("Turning Red", "Turning Red", Duration.ofMinutes(85), 11, 0), 4, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(14, 30))));
            repository.save(new Showing(new Movie("Spider-Man: No Way Home", "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 5, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(16, 10))));
            repository.save(new Showing(new Movie("The Batman", "The Batman", Duration.ofMinutes(95), 9, 0), 6, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(17, 50))));
            repository.save(new Showing(new Movie("Turning Red", "Turning Red", Duration.ofMinutes(85), 11, 0), 7, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(19, 30))));
            repository.save(new Showing(new Movie("Spider-Man: No Way Home", "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1), 8, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(21, 10))));
            repository.save(new Showing(new Movie("The Batman", "The Batman", Duration.ofMinutes(95), 9, 0), 9, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(23, 0))));
        };
    }
}
