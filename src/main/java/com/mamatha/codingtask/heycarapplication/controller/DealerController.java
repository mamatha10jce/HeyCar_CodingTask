package com.mamatha.codingtask.heycarapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mamatha.codingtask.heycarapplication.model.DealerCarJson;
import com.mamatha.codingtask.heycarapplication.model.DealerCarMapper;
import com.mamatha.codingtask.heycarapplication.service.DealerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author Mamatha
 *
 */
@Api(value = "HeyCar Dealer Details")
@RestController
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @Autowired
    private DealerCarMapper dealerCarSpecificationMapper;

    /**
     * @param dealerId
     * @param csv
     * @throws IOException
     */
    @PostMapping(value = "/upload_csv/{dealerId}/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Upload Dealer details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "A list of Cars"),
			@ApiResponse(code = 201, message = "Uploaded CSV files successfully") })
    public void sendDealerCSV(@PathVariable(required = true) Long dealerId,
            @RequestParam("file") MultipartFile csv) throws IOException {
        dealerService.saveOrUpdate(dealerCarSpecificationMapper.map(csv, dealerId));
    }

    /**
     * @param dealerCarSpecificationInJsonList
     * @param dealerId
     */
    @PostMapping(value = "/vehicle_listings/{dealerId}/")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendDealerJson(@Valid @RequestBody List<DealerCarJson> dealerCarSpecificationInJsonList,
                       @PathVariable(required = true) Long dealerId) {
        dealerService.saveOrUpdate(dealerCarSpecificationMapper.map(dealerCarSpecificationInJsonList, dealerId));
    }

}
