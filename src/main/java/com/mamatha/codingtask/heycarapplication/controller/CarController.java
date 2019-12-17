package com.mamatha.codingtask.heycarapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.mamatha.codingtask.heycarapplication.model.CarDetails;
import com.mamatha.codingtask.heycarapplication.model.CarSearchRequest;
import com.mamatha.codingtask.heycarapplication.service.CarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

/**
 * @author Mamatha
 *
 */
@Api(value = "HeyCar Car Details")
@RestController
@RequestMapping("/search")
@Slf4j
public class CarController {

	@Autowired
	private CarService carService;

	/**
	 * @param carSearchRequest
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "Get CarDetails")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "A list of Cars", response = CarDetails.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "No Car available") })
	public Page<CarDetails> search(@Valid @ModelAttribute CarSearchRequest carSearchRequest,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {

		return carService.searchCars(carSearchRequest, page, pageSize);

	}
}
