package com.diegofma.countriesdata.application.usecase;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryEntity;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryMapper;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SetDataUseCaseImplTest {

    private static final long ID = 1L;

    private static final String COUNTRY = "Spain";

    private static final long POPULATION = 47351567L;

    @InjectMocks
    private SetDataUseCaseImpl setDataUseCaseImpl;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setData_shouldUpdateExistingCountry() {
        CountryPopulation countryPopulation = CountryPopulation.builder()
                .country(COUNTRY)
                .population(POPULATION)
                .build();
        CountryEntity countryEntity = CountryEntity.builder()
                .id(ID)
                .name(COUNTRY)
                .population(POPULATION)
                .build();
        when(countryRepository.findOneByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(countryEntity));
        
        setDataUseCaseImpl.setData(Arrays.asList(countryPopulation));
        
        verify(countryRepository, times(1)).findOneByName(COUNTRY);
        verify(countryRepository, times(1)).save(countryEntity);
    }

    @Test
    public void setData_shouldCreateNewCountry() {
        CountryPopulation countryPopulation = CountryPopulation.builder()
                .country(COUNTRY)
                .population(POPULATION)
                .build();
        CountryEntity countryEntity = CountryEntity.builder()
                .id(ID)
                .name(COUNTRY)
                .population(POPULATION)
                .build();
        when(countryRepository.findOneByName(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        when(countryMapper.fromDomainToEntity(countryPopulation)).thenReturn(countryEntity);
        
        setDataUseCaseImpl.setData(Arrays.asList(countryPopulation));
        
        verify(countryRepository, times(1)).findOneByName(COUNTRY);
        verify(countryRepository, times(1)).save(countryEntity);
    }
}
