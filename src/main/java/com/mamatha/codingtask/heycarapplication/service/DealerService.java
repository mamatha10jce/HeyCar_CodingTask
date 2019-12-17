package com.mamatha.codingtask.heycarapplication.service;

import java.util.List;

import com.mamatha.codingtask.heycarapplication.model.DealerDetails;

/**
 * @author Mamatha Shivanna
 *
 */
public interface DealerService {
	public void saveOrUpdate(List<DealerDetails> uploadList);
}
