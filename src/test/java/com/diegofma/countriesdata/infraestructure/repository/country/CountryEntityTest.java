package com.diegofma.countriesdata.infraestructure.repository.country;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryEntityTest {

    private static final long ID = 1L;

    private static final String COUNTRY = "Spain";

    private static final long POPULATION = 47351567L;

    @Test
    public void testCountryEntity() {
        CountryEntity country = CountryEntity.builder()
                .id(ID)
                .name(COUNTRY)
                .population(POPULATION)
                .build();
        
        assertNotNull(country.getId());
        Assertions.assertEquals(COUNTRY, country.getName());
        Assertions.assertEquals(POPULATION, country.getPopulation());
    }

}
