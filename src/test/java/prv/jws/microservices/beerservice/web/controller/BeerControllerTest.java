package prv.jws.microservices.beerservice.web.controller;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import prv.jws.microservices.beerservice.web.model.BeerDto;
import prv.jws.microservices.beerservice.web.model.BeerStyle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BeerDto validBeer;

    @BeforeEach
    public void setup() {
        validBeer = BeerDto.builder().id(UUID.randomUUID())
                .beerName("Beer")
                .beerStyle(BeerStyle.IPA)
                .price(new BigDecimal("7.50"))
                .upc(189273L)
                .build();
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beers/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beers/").contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beers/"+UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}