package com.pradeep.reviewms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewMessage {
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;
}
