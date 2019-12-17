package com.mamatha.codingtask.heycarapplication.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mamatha.codingtask.heycarapplication.constant.HeyCarAppConstants;
import com.mamatha.codingtask.heycarapplication.dao.CarDetailsSpecification;
import com.mamatha.codingtask.heycarapplication.model.CarDetails;
import com.mamatha.codingtask.heycarapplication.model.CarSearchRequest;
import com.mamatha.codingtask.heycarapplication.repository.CarDetailsRepository;
import com.mamatha.codingtask.heycarapplication.service.CarService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDetailsRepository repository;

	@Transactional
	public Long createCar(CarDetails carDetails) {
		CarDetails saved = repository.save(carDetails);

		return saved.getId();
	}

	@Transactional
	public void updateCar(CarDetails source) {
		Optional<CarDetails> optionalExisting = repository.findById(source.getId());
		if (optionalExisting.isPresent()) {
			CarDetails existing = optionalExisting.get();
			existing.setColor(source.getColor());
			existing.setPower(source.getPower());
			existing.setMake(source.getMake());
			existing.setModel(source.getModel());
			existing.setPrice(source.getPrice());
		} else {
			throw new EntityNotFoundException("Car not found");
		}
	}

	/**
	 *
	 * @param carSearchRequest
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@Transactional
	public Page<CarDetails> searchCars(CarSearchRequest carSearchRequest, int page, int pageSize) {
		Map<String, Object> searchParams = getSearchCriteriaInMap(carSearchRequest);

		if (searchParams.isEmpty()) {
			return repository.findAll(PageRequest.of(page, pageSize));
		}

		Specification<CarDetails> spec = null;
		if (searchParams.containsKey(HeyCarAppConstants.MAKE)) {
			Specification<CarDetails> byParam = CarDetailsSpecification
					.findByMake(searchParams.get(HeyCarAppConstants.MAKE));
			spec = spec != null ? spec.and(byParam) : byParam;
		}
		if (searchParams.containsKey(HeyCarAppConstants.MODEL)) {
			Specification<CarDetails> byParam = CarDetailsSpecification
					.findByModel(searchParams.get(HeyCarAppConstants.MODEL));
			spec = spec != null ? spec.and(byParam) : byParam;
		}
		if (searchParams.containsKey(HeyCarAppConstants.YEAR)) {
			Specification<CarDetails> byParam = CarDetailsSpecification
					.findByYear(searchParams.get(HeyCarAppConstants.YEAR));
			spec = spec != null ? spec.and(byParam) : byParam;
		}
		if (searchParams.containsKey(HeyCarAppConstants.COLOR)) {
			Specification<CarDetails> byParam = CarDetailsSpecification
					.findByColour(searchParams.get(HeyCarAppConstants.COLOR));
			spec = spec != null ? spec.and(byParam) : byParam;
		}

		return spec != null ? repository.findAll(spec, PageRequest.of(page, pageSize))
				: repository.findAll(PageRequest.of(page, pageSize));
	}

	/**
	 *
	 * @param carSearchRequest
	 * @return
	 */
	private Map<String, Object> getSearchCriteriaInMap(CarSearchRequest carSearchRequest) {
		Map<String, Object> searchParams = new HashMap<>();
		Optional.ofNullable(carSearchRequest.getMake()).ifPresent(m -> searchParams.put(HeyCarAppConstants.MAKE, m));
		Optional.ofNullable(carSearchRequest.getModel()).ifPresent(m -> searchParams.put(HeyCarAppConstants.MODEL, m));
		Optional.ofNullable(carSearchRequest.getYear()).ifPresent(m -> searchParams.put(HeyCarAppConstants.YEAR, m));
		Optional.ofNullable(carSearchRequest.getColor()).ifPresent(m -> searchParams.put(HeyCarAppConstants.COLOR, m));
		return searchParams;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Optional<CarDetails> get(long id) {
		return repository.findById(id);
	}
}
