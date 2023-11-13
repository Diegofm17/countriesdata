package com.diegofma.countriesdata.infraestructure.repository.country;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    
    @Mapping(source = "name", target = "country")
    CountryPopulation fromEntityToDomain(CountryEntity entity);

    List<CountryPopulation> fromEntityToDomain(List<CountryEntity> entity);

    @Mapping(source = "country", target = "name")
    CountryEntity fromDomainToEntity(CountryPopulation entity);

    List<CountryEntity> fromDomainToEntity(List<CountryPopulation> entity);
}
