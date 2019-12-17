package com.mamatha.codingtask.heycarapplication.repository;

import com.mamatha.codingtask.heycarapplication.model.CarDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mamatha
 *
 */
@Repository
public interface CarDetailsRepository extends CrudRepository<CarDetails, Long>, JpaSpecificationExecutor<CarDetails> {
	Page<CarDetails> findAll(Pageable pageable);
}
