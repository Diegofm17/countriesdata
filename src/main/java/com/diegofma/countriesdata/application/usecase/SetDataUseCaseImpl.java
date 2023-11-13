package com.diegofma.countriesdata.application.usecase;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import com.diegofma.countriesdata.domain.usecase.SetDataUseCase;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryEntity;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryMapper;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SetDataUseCaseImpl implements SetDataUseCase {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public SetDataUseCaseImpl(final CountryRepository countryRepository, final CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }
    
    @Override
    public void setData(List<CountryPopulation> countryPopulationList) {

        for (CountryPopulation countryPopulation: countryPopulationList) {
            Optional<CountryEntity> countries = this.countryRepository.findOneByName(countryPopulation.getCountry());

            if(countries.isPresent()){
                CountryEntity countryEntityUpdated = countries.get();
                countryEntityUpdated.setPopulation(countryPopulation.getPopulation());
                this.countryRepository.save(countryEntityUpdated);
            }else {
                this.countryRepository.save(this.countryMapper.fromDomainToEntity(countryPopulation));
            }
        }
    }
}
