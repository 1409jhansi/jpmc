package com.jpmc.theater;

import com.jpmc.theater.error.MovieUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import com.jpmc.theater.vo.*;

@RestController
public class TheaterController {

    private static int MOVIE_CODE_SPECIAL = 1;
    @Autowired
    private ShowingRepository repository;

    // Find
    @GetMapping("/movie")
    List<Showing> findAll() {
        List<Showing> showingList = repository.findAll();
        ListIterator showingListItr = showingList.listIterator();
        while(showingListItr.hasNext()){
            Showing showing = (Showing)showingListItr.next();
            double discountedPrice = calculateTicketPrice(showing);
            showing.getMovie().setTicketPrice(discountedPrice);
        }
        return showingList;
    }
    public double calculateTicketPrice(Showing showing) {
        return showing.getMovie().getTicketPrice() - getDiscount(showing.getSequenceOfTheDay(),showing.getMovie().getSpecialCode(),showing.getMovie().getTicketPrice(),showing.getShowStartTime());
    }

    private double getDiscount(int showSequence, int specialCode, double ticketPrice, LocalDateTime showStartTime) {
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        try {
            if (showSequence == 1) {
                sequenceDiscount = 3; // $3 discount for 1st show
            } else if (showSequence == 2) {
                sequenceDiscount = 2; // $2 discount for 2nd show
            } else if (showSequence == 7) {
                sequenceDiscount = 1; // $1 discount for 7th show
            }
        }
       catch (Exception ex) {
           Set errorSet = new HashSet<String>();
           errorSet.add(ex.getMessage());
            throw new MovieUnSupportedFieldPatchException(errorSet);
        }

        LocalTime start = LocalTime.of( 11 , 0 );
        LocalTime end = LocalTime.of( 16 , 0 );
        double nonPeakTimeDiscount = 0;
        if(showStartTime.toLocalTime().isBefore(end) || showStartTime.toLocalTime().isAfter(start)){
            nonPeakTimeDiscount = ticketPrice * 0.25;  // 25% discount for special movie
        }

        // biggest discount wins
        return nonPeakTimeDiscount > (specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount) ? nonPeakTimeDiscount:((specialDiscount > sequenceDiscount) ? specialDiscount : sequenceDiscount);
    }

}

