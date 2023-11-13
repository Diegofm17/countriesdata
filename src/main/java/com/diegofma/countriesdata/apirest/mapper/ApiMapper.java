package com.diegofma.countriesdata.apirest.mapper;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import org.mapstruct.Mapper;
import org.openapitools.model.CountryPopulationDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApiMapper {
    
    CountryPopulation fromCountryPopulationDTO(CountryPopulationDTO src);

    List<CountryPopulation> fromCountryPopulationDTO(List<CountryPopulationDTO> src);

    CountryPopulationDTO toCountryPopulationDTO(CountryPopulation src);

    List<CountryPopulationDTO> toCountryPopulationDTO(List<CountryPopulation> src);
}
