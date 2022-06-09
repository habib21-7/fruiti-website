package com.example.webservices.order_management.Controller;


import com.example.webservices.order_management.Dto.ProductDTO;
import com.example.webservices.order_management.Dto.StockDTO;
import com.example.webservices.order_management.Model.Product;
import com.example.webservices.order_management.Model.Stock;
import com.example.webservices.order_management.Services.serviceImpl.ProductServices;
import com.example.webservices.order_management.Services.serviceImpl.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockServices stockServices;

    @GetMapping("")
    public List<Stock> getStocks(){
        return stockServices.getAllStocks();
    }


    @PostMapping("")
    public ResponseEntity<Stock> create(@RequestBody Stock stock) throws URISyntaxException {

        Stock result = stockServices.save(stock);
        return ResponseEntity.created(new URI("/api/stock/" + result.getStock_id())).body(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock
            (@Valid @RequestBody StockDTO stockDTO
                    , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(stockServices.updateStock(stockDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id) {
        stockServices.deleteStockById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
