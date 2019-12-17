package com.mamatha.codingtask.heycarapplication.service;

import org.springframework.data.domain.Page;
import com.mamatha.codingtask.heycarapplication.model.CarDetails;
import com.mamatha.codingtask.heycarapplication.model.CarSearchRequest;

/**
 * @author Mamatha Shivanna
 *
 */
public interface CarService {

	public Long createCar(CarDetails carDetails);

	public void updateCar(CarDetails input);

	public Page<CarDetails> searchCars(CarSearchRequest searchParams, int page, int pageSize);
}
