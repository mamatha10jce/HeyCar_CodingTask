package com.mamatha.codingtask.heycarapplication.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class DealerDetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column
	private long dealerId;

	@OneToOne
	private CarDetails carDetails;

	@Column
	private String listingId;

	public DealerDetails(long dealerId, CarDetails carDetails, String listingId) {
		super();
		this.dealerId = dealerId;
		this.carDetails = carDetails;
		this.listingId = listingId;
	}

}
