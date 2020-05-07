package com.kolmikra.repository;

import com.kolmikra.model.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends CommonRepository<Store> {

    @Query(value = "SELECT s.name FROM Store s WHERE s.neighborhood = :firstNeighborhood " +
            "OR s.neighborhood = :secondNeighborhood")
    List<String> getStoreNameByNeighborhood(@Param("firstNeighborhood") String firstNeighborhood,
                                            @Param("secondNeighborhood") String secondNeighborhood);
}
