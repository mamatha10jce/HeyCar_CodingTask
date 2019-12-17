package com.mamatha.codingtask.heycarapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerCarJson {

	@NotEmpty
	private String code;

	@NotEmpty
	private String make;

	@NotEmpty
	private String model;

	@NotNull
	@JsonProperty("power")
	private Integer power;

	@NotEmpty
	private String color;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Integer year;
}
