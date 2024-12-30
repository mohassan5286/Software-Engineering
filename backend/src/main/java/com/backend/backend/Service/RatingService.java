package com.backend.backend.Service;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Entity.Rating;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.RatingRepository;
import com.backend.backend.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepo;
    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    DestinationRepository destinationRepo;


    public void rate( Rating rating){
        ratingRepo.save(rating);
        getAverageRating(rating.getPid());
    }




    public Rating getRating( String Uid, String Pid){
        return ratingRepo.findByUserIdAndPid(Uid, Pid);
    }

    public double getAverageRating(String Pid){
        try {
            System.out.println(Pid);
            List<Rating>ratings = ratingRepo.findByPid(Pid);
            Destination destination = destinationRepo.findDestinationById(Pid);
            double averageRating = 0;
            for (Rating rating : ratings) {
                averageRating+=rating.getRatingScore();
            }
            averageRating=averageRating/((double)ratings.size());
            destination.setRating(averageRating);
            destination.setNo_of_reviews(ratings.size());
//            System.out.println(destination);
            destinationRepo.save(destination);
            return averageRating;
        }
        catch (Exception e){
            e.printStackTrace();

        }
         return 0;
    }

}
