package com.mamatha.codingtask.heycarapplication.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class CarDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column
	private String make;

	@Column
	private String model;

	@Column
	private Integer power;

	@Column
	private String color;

	@Column
	private BigDecimal price;

	@Column
	private Integer year;

	public CarDetails(String make, String model, Integer power, String color, BigDecimal price, Integer year) {
		super();
		this.make = make;
		this.model = model;
		this.power = power;
		this.color = color;
		this.price = price;
		this.year = year;
	}

}
