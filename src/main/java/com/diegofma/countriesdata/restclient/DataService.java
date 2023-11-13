package com.diegofma.countriesdata.restclient;

import com.diegofma.countriesdata.domain.entity.CountryPopulation;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryEntity;
import com.diegofma.countriesdata.infraestructure.repository.country.CountryRepository;
import com.diegofma.countriesdata.restclient.dto.CountryDTO;
import com.diegofma.countriesdata.restclient.dto.NameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataService implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final CountryRepository countryRepository;

    @Autowired
    public DataService(RestTemplate restTemplate, CountryRepository countryRepository) {
        this.restTemplate = restTemplate;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) {
        fetchDataAndSaveToDatabase();
    }

    private void fetchDataAndSaveToDatabase() {
        String apiUrl = "https://restcountries.com/v3.1/all?fields=name,population";
        CountryDTO[] data = restTemplate.getForObject(apiUrl, CountryDTO[].class);
        
        if (data != null) {
            for (CountryDTO countryDTO : data) {
                countryRepository.save(CountryEntity.builder()
                        .name(countryDTO.getName().getCommon())
                        .population((long) countryDTO.getPopulation())
                        .build());
            }
        }
    }
}
