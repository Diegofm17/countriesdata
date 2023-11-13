package com.diegofma.countriesdata.apirest.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import com.diegofma.countriesdata.domain.usecase.GetDataUseCase;
import com.diegofma.countriesdata.domain.usecase.SetDataUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.CountryPopulationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diegofma.countriesdata.apirest.mapper.ApiMapper;

import javax.persistence.NoResultException;

public class ApiControllerTest {

    private static final String COUNTRY = "Spain";
    
    private static final long POPULATION = 47351567L;

    @Mock
    private GetDataUseCase getDataUseCase;

    @Mock
    private SetDataUseCase setDataUseCase;
    
    @Mock
    private ApiMapper apiMapper;

    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetData() {
        List<CountryPopulation> result = new ArrayList<>();
        result.add(CountryPopulation.builder().country(COUNTRY).population(POPULATION).build());
        when(getDataUseCase.getData(ArgumentMatchers.anyString())).thenReturn(result);
        
        List<CountryPopulationDTO> expected = new ArrayList<>();
        CountryPopulationDTO countryPopulationDTO = new CountryPopulationDTO();
        countryPopulationDTO.setCountry(COUNTRY);
        countryPopulationDTO.setPopulation(POPULATION);
        expected.add(countryPopulationDTO);
        when(apiMapper.toCountryPopulationDTO(result)).thenReturn(expected);
        
        ResponseEntity<List<CountryPopulationDTO>> response = apiController.getData(COUNTRY);
        
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expected, response.getBody());
    }

    @Test
    public void testGetDataNoResult() {
        when(getDataUseCase.getData(ArgumentMatchers.anyString())).thenReturn(null);
        
        Assertions.assertThrows(NoResultException.class, () -> apiController.getData(COUNTRY));
    }

    @Test
    public void testGetDataEmptyResult() {
        when(getDataUseCase.getData(ArgumentMatchers.anyString())).thenReturn(new ArrayList<>());
        
        Assertions.assertThrows(NoResultException.class, () -> apiController.getData(COUNTRY));
    }

    @Test
    public void testSetData() {
        List<CountryPopulationDTO> input = new ArrayList<>();
        CountryPopulationDTO countryPopulationDTO = new CountryPopulationDTO();
        countryPopulationDTO.setCountry(COUNTRY);
        countryPopulationDTO.setPopulation(POPULATION);
        input.add(countryPopulationDTO);
        List<CountryPopulation> expected = new ArrayList<>();
        expected.add(CountryPopulation.builder().country(COUNTRY).population(POPULATION).build());
        when(apiMapper.fromCountryPopulationDTO(input)).thenReturn(expected);
        
        ResponseEntity<Void> response = apiController.setData(input);
        
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testSetDataNullInput() {
        when(apiMapper.fromCountryPopulationDTO(ArgumentMatchers.any(CountryPopulationDTO.class))).thenReturn(null);
        
        ResponseEntity<Void> response = apiController.setData(new ArrayList<>());
        
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
