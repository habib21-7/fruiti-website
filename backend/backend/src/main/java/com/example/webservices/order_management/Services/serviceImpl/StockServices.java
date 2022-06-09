package com.example.webservices.order_management.Services.serviceImpl;

import com.example.webservices.order_management.Dto.StockDTO;
import com.example.webservices.order_management.Exeptions.ResourceNotFoundException;
import com.example.webservices.order_management.Model.Product;
import com.example.webservices.order_management.Model.Stock;
import com.example.webservices.order_management.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServices {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAllStocks();
    }


    public Stock save(Stock stock) {

        return stockRepository.save(stock);
    }


    public StockDTO updateStock(StockDTO stockDTO, long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("stock","id",id));
        stock.setQuantity(stockDTO.getQuantity());
        stock.setUpdateAt(stockDTO.getUpdateAt());


        Stock updatedStock = stockRepository.save(stock);
        return mapToDTO(updatedStock);
    }

    public void deleteStockById(long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("stock", "id", id));
        stockRepository.delete(stock);
    }

    private StockDTO mapToDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setQuantity(stockDTO.getQuantity());
        stockDTO.setUpdateAt(stockDTO.getUpdateAt());

        return stockDTO;
    }

}
