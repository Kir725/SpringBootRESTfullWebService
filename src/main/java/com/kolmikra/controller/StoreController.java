package com.kolmikra.controller;

import com.kolmikra.model.Store;
import com.kolmikra.service.StoreService;
import com.kolmikra.view.StoreTitleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/store")
public class StoreController extends AbstractController<Store, StoreService> {

    @Autowired
    StoreService storeService;

    @GetMapping("/getStoreTitleByNeighborhood")
    public ResponseEntity<List<StoreTitleView>> getStoreTitleByNeighborhood(@RequestParam String firstNeighborhood,
                                                                    String secondNeighborhood) {
        List<StoreTitleView> result = storeService.getStoreNameByNeighborhood(firstNeighborhood, secondNeighborhood);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
