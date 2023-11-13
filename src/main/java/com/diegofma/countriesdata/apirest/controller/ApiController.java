package com.diegofma.countriesdata.apirest.controller;

import com.diegofma.countriesdata.apirest.mapper.ApiMapper;
import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import com.diegofma.countriesdata.domain.usecase.GetDataUseCase;
import com.diegofma.countriesdata.domain.usecase.SetDataUseCase;
import org.openapitools.api.ApiApi;
import org.openapitools.model.CountryPopulationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import java.util.List;

@RestController
public class ApiController implements ApiApi {

    @Autowired
    private GetDataUseCase getDataUseCase;

    @Autowired
    private SetDataUseCase setDataUseCase;
    
    @Autowired
    private ApiMapper apiMapper;

    @Override
    public ResponseEntity<List<CountryPopulationDTO>> getData(String country) {
        List<CountryPopulation> result = this.getDataUseCase.getData(country);
        if(result == null || result.size() == 0){
            throw new NoResultException();
        }
        return ResponseEntity.ok(this.apiMapper.toCountryPopulationDTO(result));
    }

    @Override
    public ResponseEntity<Void> setData(List<CountryPopulationDTO> countryPopulationDTOList) {
        this.setDataUseCase.setData(this.apiMapper.fromCountryPopulationDTO(countryPopulationDTOList));
        return ResponseEntity.noContent().build();
    }
}
