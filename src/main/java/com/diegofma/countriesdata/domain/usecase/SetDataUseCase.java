package com.diegofma.countriesdata.domain.usecase;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;

import java.util.List;

public interface SetDataUseCase {

    void setData(List<CountryPopulation> countryPopulation);
}
