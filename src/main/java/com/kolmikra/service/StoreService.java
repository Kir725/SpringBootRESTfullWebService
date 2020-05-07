package com.kolmikra.service;

import com.kolmikra.model.Store;
import com.kolmikra.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoreService extends AbstractService<Store, StoreRepository> {

    @Autowired
    StoreRepository storeRepository;

    public List<String> getStoreNameByNeighborhood(String firstNeighborhood, String secondNeighborhood) {
        return storeRepository.getStoreNameByNeighborhood(firstNeighborhood, secondNeighborhood);
    }
}
