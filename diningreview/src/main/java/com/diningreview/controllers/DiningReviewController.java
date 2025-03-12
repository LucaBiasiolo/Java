package com.diningreview.controllers;

import com.diningreview.entities.DiningReview;
import com.diningreview.services.DiningReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diningreviews")
@CrossOrigin(origins = "http://localhost:4200")
public class DiningReviewController {

    @Autowired
    private DiningReviewService diningReviewService;

    public DiningReview saveDiningReview(DiningReview diningReview){
        return diningReviewService.save(diningReview);
    }
}
