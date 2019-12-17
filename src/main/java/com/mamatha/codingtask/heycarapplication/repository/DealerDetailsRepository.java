package com.mamatha.codingtask.heycarapplication.repository;

import com.mamatha.codingtask.heycarapplication.model.DealerDetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mamatha
 *
 */
@Repository
public interface DealerDetailsRepository extends CrudRepository<DealerDetails, Long> {

	Optional<DealerDetails> findByDealerIdAndListingId(long dealerId, String listingId);
}
