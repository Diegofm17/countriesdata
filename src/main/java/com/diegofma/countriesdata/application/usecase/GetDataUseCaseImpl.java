package com.diegofma.countriesdata.application.usecase;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import com.diegofma.countriesdata.domain.usecase.GetDataUseCase;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryEntity;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryMapper;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetDataUseCaseImpl implements GetDataUseCase {
    
    private final CountryRepository countryRepository;
    
    private final CountryMapper countryMapper;

    public GetDataUseCaseImpl(final CountryRepository countryRepository, final CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<CountryPopulation> getData(String country) {
        if(country == null){
            return this.countryMapper.fromEntityToDomain(countryRepository.findAll());
        }

        Optional<List<CountryEntity>> countries = this.countryRepository.findByName(country);
        return countries.map(this.countryMapper::fromEntityToDomain).orElse(null);
    }
}
