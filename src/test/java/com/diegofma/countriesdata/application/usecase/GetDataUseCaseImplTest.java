package com.diegofma.countriesdata.application.usecase;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryEntity;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryMapper;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class GetDataUseCaseImplTest {

    private static final long ID = 1L;

    private static final String COUNTRY = "Spain";

    private static final long POPULATION = 47351567L;

    @InjectMocks
    private GetDataUseCaseImpl getDataUseCaseImpl;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDataWithNullCountry() {
        List<CountryEntity> countryEntities = Arrays.asList(new CountryEntity(ID, COUNTRY, POPULATION));
        when(countryRepository.findAll()).thenReturn(countryEntities);

        List<CountryPopulation> expected = Arrays.asList(CountryPopulation.builder()
                .country(COUNTRY)
                .population(POPULATION).build());
        when(countryMapper.fromEntityToDomain(ArgumentMatchers.anyList())).thenReturn(expected);
        
        List<CountryPopulation> result = getDataUseCaseImpl.getData(null);
        
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetDataWithCountry() {
        List<CountryEntity> countryEntities = Arrays.asList(new CountryEntity(ID, COUNTRY, POPULATION));
        when(countryRepository.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(countryEntities));

        List<CountryPopulation> expected = Arrays.asList(CountryPopulation.builder()
                .country(COUNTRY)
                .population(POPULATION).build());
        when(countryMapper.fromEntityToDomain(ArgumentMatchers.anyList())).thenReturn(expected);

        List<CountryPopulation> result = getDataUseCaseImpl.getData(COUNTRY);

        Assertions.assertEquals(expected, result);
    }
}
