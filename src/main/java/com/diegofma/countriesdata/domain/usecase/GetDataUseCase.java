package com.diegofma.countriesdata.domain.usecase;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;

import java.util.List;

public interface GetDataUseCase {

    List<CountryPopulation> getData(String country);
}
