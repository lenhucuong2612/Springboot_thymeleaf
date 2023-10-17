package com.example.DoAn.server;

import com.example.DoAn.entity.OrderDetail;
import com.example.DoAn.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repository;
    List<OrderDetail> findSize(Long id){
        return repository.findAllByOrder_Id(id);
    }
}
