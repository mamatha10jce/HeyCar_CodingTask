package com.mamatha.codingtask.heycarapplication.dao;

import org.springframework.data.jpa.domain.Specification;

import com.mamatha.codingtask.heycarapplication.model.CarDetails;

/**
 * @author Mamatha
 *
 */
public class CarDetailsSpecification {

	public static Specification<CarDetails> findByModel(Object model) {
		return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("model"),
				model);
	}

	public static Specification<CarDetails> findByMake(Object make) {
		return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("make"),
				make);
	}

	public static Specification<CarDetails> findByYear(Object year) {
		return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("year"),
				year);
	}

	public static Specification<CarDetails> findByColour(Object colour) {
		return (carListingRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(carListingRoot.get("color"),
				colour);
	}

}
