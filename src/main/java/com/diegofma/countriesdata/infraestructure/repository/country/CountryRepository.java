package com.diegofma.countriesdata.infraestructure.repository.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    @Query(value = "SELECT * FROM COUNTRY c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))",
            nativeQuery = true)
    Optional<List<CountryEntity>> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM COUNTRY c WHERE LOWER(c.name) LIKE LOWER(:name)",
            nativeQuery = true)
    Optional<CountryEntity> findOneByName(@Param("name") String name);
}
