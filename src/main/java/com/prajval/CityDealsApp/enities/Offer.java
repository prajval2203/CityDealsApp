package com.prajval.CityDealsApp.enities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private double discountPercentage;

    @CreationTimestamp
    private LocalDate startDate;
    @UpdateTimestamp
    private LocalDate endDate;
    private boolean isActive = true;

    @ManyToOne
    private Shop shop;
}
