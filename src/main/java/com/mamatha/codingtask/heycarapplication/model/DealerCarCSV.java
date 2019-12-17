package com.mamatha.codingtask.heycarapplication.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerCarCSV {

	@NotEmpty
	private String code;

	@NotEmpty
	@JsonAlias("make/model")
	private String makeAndModel;

	@NotEmpty
	@JsonAlias("power-in-ps")
	private Integer power;

	@NotEmpty
	private Integer year;

	@NotEmpty
	private String color;

	@NotNull
	private BigDecimal price;
}
