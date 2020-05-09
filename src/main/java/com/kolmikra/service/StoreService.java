package com.kolmikra.service;

import com.kolmikra.model.Store;
import com.kolmikra.repository.StoreRepository;
import com.kolmikra.view.StoreTitleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StoreService extends AbstractService<Store, StoreRepository> {

    @Autowired
    StoreRepository storeRepository;

    public List<StoreTitleView> getStoreNameByNeighborhood(String firstNeighborhood, String secondNeighborhood) {
        return storeRepository.findByNeighborhoodOrNeighborhood(firstNeighborhood, secondNeighborhood);
    }
}
