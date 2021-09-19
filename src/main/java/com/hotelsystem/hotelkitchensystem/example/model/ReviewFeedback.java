package com.hotelsystem.hotelkitchensystem.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="reviewFeedback")

public class ReviewFeedback {
    @Id
    @GeneratedValue
    private int feedbackId;
    private String feedback;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
