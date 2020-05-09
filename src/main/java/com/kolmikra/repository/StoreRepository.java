package com.kolmikra.repository;

import com.kolmikra.model.Store;
import com.kolmikra.view.StoreTitleView;

import java.util.List;

public interface StoreRepository extends CommonRepository<Store> {

    List<StoreTitleView> findByNeighborhoodOrNeighborhood(String firstNeighborhood, String secondNeighborhood);

}
