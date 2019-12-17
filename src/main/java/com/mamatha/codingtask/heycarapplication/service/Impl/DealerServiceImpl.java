package com.mamatha.codingtask.heycarapplication.service.Impl;

import com.mamatha.codingtask.heycarapplication.model.CarDetails;
import com.mamatha.codingtask.heycarapplication.model.DealerDetails;
import com.mamatha.codingtask.heycarapplication.repository.DealerDetailsRepository;
import com.mamatha.codingtask.heycarapplication.service.CarService;
import com.mamatha.codingtask.heycarapplication.service.DealerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

	@Autowired
	private DealerDetailsRepository dealerListingRepository;

	@Autowired
	private CarService carService;

	@Override
	public void saveOrUpdate(List<DealerDetails> uploadDetailsList) {

		uploadDetailsList.forEach(carListing -> {
			if (!dealerListingRepository.findByDealerIdAndListingId(carListing.getDealerId(), carListing.getListingId())
					.isPresent()) {
				create(carListing);
			} else {
				update(carListing);
			}
		});

	}

	@Transactional
	private long create(DealerDetails carDetails) {
		carService.createCar(carDetails.getCarDetails());
		return dealerListingRepository.save(carDetails).getId();
	}

	@Transactional
	private void update(DealerDetails source) {
		Optional<DealerDetails> existing = dealerListingRepository.findByDealerIdAndListingId(source.getDealerId(),
				source.getListingId());
		if (existing.isPresent()) {
			DealerDetails dealerCarListing = existing.get();
			CarDetails sourceCar = source.getCarDetails();
			CarDetails targetListing = dealerCarListing.getCarDetails();

			targetListing.setPrice(sourceCar.getPrice());
			targetListing.setModel(sourceCar.getModel());
			targetListing.setMake(sourceCar.getMake());
			targetListing.setPower(sourceCar.getPower());
			targetListing.setColor(sourceCar.getColor());
			targetListing.setYear(sourceCar.getYear());
			dealerListingRepository.save(dealerCarListing);
		}
	}
}
