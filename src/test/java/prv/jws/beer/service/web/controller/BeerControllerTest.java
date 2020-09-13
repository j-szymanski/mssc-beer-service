package prv.jws.beer.service.web.controller;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import prv.jws.beer.service.services.BeerService;
import prv.jws.beer.service.web.model.BeerDto;
import prv.jws.beer.service.web.model.BeerStyle;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

    @MockBean
    BeerService beerService;


    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(any())).willReturn(getValidBeerDto());
        mockMvc.perform(get("/api/v1/beers/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());
        BeerDto beerDto = getValidBeerDto();
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beers/").contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        given(beerService.updateBeerById(any(), any())).willReturn(getValidBeerDto());
        BeerDto beerDto = getValidBeerDto();
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beers/"+UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    private BeerDto getValidBeerDto() {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Beer")
                .beerStyle(BeerStyle.IPA)
                .price(new BigDecimal("7.50"))
                .upc("44232123")
                .build();
    }
}