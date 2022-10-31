package com.moviecatalog.moviecatalogservice.Controller;

import com.moviecatalog.moviecatalogservice.Model.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){
        return Collections.singletonList(
                new CatalogItem("Transformers", "test", 4)
        );
    }
}
