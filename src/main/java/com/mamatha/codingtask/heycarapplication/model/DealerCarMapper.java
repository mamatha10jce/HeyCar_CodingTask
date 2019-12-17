package com.mamatha.codingtask.heycarapplication.model;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DealerCarMapper {

	public List<DealerDetails> map(MultipartFile csv, Long dealerId) throws IOException {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();

		ObjectReader objectReader = csvMapper.readerFor(DealerCarCSV.class).with(schema);

		List<DealerCarCSV> dealerCarCSVList = new ArrayList<>();
		try (Reader reader = new FileReader(convertMultiPartToFile(csv))) {
			MappingIterator<DealerCarCSV> dealerCarSpecificationsMappingIterator = objectReader.readValues(reader);
			while (dealerCarSpecificationsMappingIterator.hasNext()) {
				dealerCarCSVList.add(dealerCarSpecificationsMappingIterator.next());
			}
		}

		return dealerCarCSVList.stream().map(dealerCarInCSV -> {
			String[] makeAndModel = dealerCarInCSV.getMakeAndModel().split("/");
			return new DealerDetails(dealerId,
					new CarDetails(makeAndModel[0], makeAndModel[1], dealerCarInCSV.getPower(),
							dealerCarInCSV.getColor(), dealerCarInCSV.getPrice(), dealerCarInCSV.getYear()),
					dealerCarInCSV.getCode());
		}).collect(Collectors.toList());
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File("temp");
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public List<DealerDetails> map(List<DealerCarJson> dealerCarList, Long dealerId) {
		return dealerCarList.stream().map(dealerCarJson -> {
			return new DealerDetails(dealerId,
					new CarDetails(dealerCarJson.getMake(), dealerCarJson.getModel(), dealerCarJson.getPower(),
							dealerCarJson.getColor(), dealerCarJson.getPrice(), dealerCarJson.getYear()),
					dealerCarJson.getCode());
		}).collect(Collectors.toList());
	}
}
