package com.mamatha.codingtask.heycarapplication.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mamatha.codingtask.heycarapplication.model.CarDetails;
import com.mamatha.codingtask.heycarapplication.repository.CarDetailsRepository;
import com.mamatha.codingtask.heycarapplication.repository.DealerDetailsRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DealerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CarDetailsRepository repository;

  @Autowired
  private DealerDetailsRepository dealerRepository;

  @After
  public void after(){
       dealerRepository.deleteAll();
       repository.deleteAll();
  }

  @Test
  public void testCreateWithJSON() throws Exception {
      mockMvc.perform(post("/vehicle_listings/123/").content(
        "[\n"
            + "{\n"
            + "\"code\": \"a\",\n"
            + "\"make\": \"renault\",\n"
            + "\"model\": \"megane\",\n"
            + "\"kW\": 132,\n"
            + "\"year\": 2014,\n"
            + "\"color\": \"red\",\n"
            + "\"price\": 13990\n"
            + "}\n"
            + "]\n"
    )
        .contentType("application/json"))
        .andExpect(status().isCreated());

    List<CarDetails> result = (List<CarDetails>) repository.findAll();

    Assert.assertEquals(1, result.size());
    Assert.assertEquals("renault", result.get(0).getMake());
  }

  @Test
  public void testCreateWithCSV() throws Exception {
    String fileContents = "code,make/model,power-in-ps,year,color,price\n"
        + "1,mercedes/a 180,123,2014,black,15950\n"
        + "2,audi/a3,111,2016,white,17210\n"
        + "3,vw/golf,86,2018,green,14980\n"
        + "4,skoda/octavia,86,2018,16990";

    MockMultipartFile csvFile = new MockMultipartFile(
        "test.json", "", "multipart/form-data", fileContents.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_csv/1/")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isCreated());

    List<CarDetails> result = (List<CarDetails>) repository.findAll();

    Assert.assertEquals(4, result.size());

    Assert.assertEquals("mercedes", result.get(0).getMake());
    Assert.assertEquals("a 180", result.get(0).getModel());
    Assert.assertEquals(2014, result.get(0).getYear().intValue());
    Assert.assertEquals("15950.00", result.get(0).getPrice().toString());
    Assert.assertEquals("black", result.get(0).getColor());

    Assert.assertEquals("audi", result.get(1).getMake());
    Assert.assertEquals("vw", result.get(2).getMake());
    Assert.assertEquals("skoda", result.get(3).getMake());


  }

  @Test
  public void testUpdateWithCSV() throws Exception {

    String fileContents = "code,make/model,power-in-ps,year,color,price\n"
        + "1,mercedes/a 180,123,2014,black,15950\n"
        + "2,audi/a3,111,2016,white,17210\n"
        + "3,vw/golf,86,2018,green,14980\n"
        + "4,skoda/octavia,86,2018,16990";

    MockMultipartFile csvFile = new MockMultipartFile(
        "test.json", "", "multipart/form-data", fileContents.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_csv/1/")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isCreated());

     fileContents = "code,make/model,power-in-ps,year,color,price\n"
        + "1,mercedes/a 180,123,1984,orange,15950\n"
        + "2,audi/a3,111,2016,white,17210\n"
        + "3,vw/golf,86,2015,green,14980\n"
        + "4,skoda/octavia,86,2018,16990";

     csvFile = new MockMultipartFile(
        "test.json", "", "multipart/form-data", fileContents.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_csv/1/")
        .file("file", csvFile.getBytes())
        .characterEncoding("UTF-8"))
        .andExpect(status().isCreated());

    List<CarDetails> result = (List<CarDetails>) repository.findAll();

    Assert.assertEquals(4, result.size());

    Assert.assertEquals("mercedes", result.get(0).getMake());
    Assert.assertEquals("a 180", result.get(0).getModel());
    Assert.assertEquals(1984, result.get(0).getYear().intValue());
    Assert.assertEquals("15950.00", result.get(0).getPrice().toString());
    Assert.assertEquals("orange", result.get(0).getColor());
  }

}
