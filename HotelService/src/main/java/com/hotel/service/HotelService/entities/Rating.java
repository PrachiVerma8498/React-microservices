package com.hotel.service.HotelService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

	private int ratingId;
	private String userId;
	private String hotelId;
	private String remark;
	private int rating;
	private Hotel hotel;
}
