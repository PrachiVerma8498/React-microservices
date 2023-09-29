package com.service.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="micro_user_rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int ratingId;
	private String userId;
	private String hotelId;
	private String remark;
	private int rating;
}
