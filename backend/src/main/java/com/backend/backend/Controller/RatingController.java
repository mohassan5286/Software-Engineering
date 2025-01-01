package com.backend.backend.Controller;

import com.backend.backend.Entity.Rating;
import com.backend.backend.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
@CrossOrigin()
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/rate")
    public void rate(@RequestBody Rating rating){
        Rating rating1=new Rating(rating.getUserId(),rating.getPid(), rating.getRatingScore());
        ratingService.rate(rating1);
    }



    @GetMapping("/get/{Uid}/{Pid}")
    public Rating getRating(@PathVariable String Uid,@PathVariable String Pid){
        return ratingService.getRating(Uid,Pid);
    }

}
