package com.example.DoAn.repository;

import com.example.DoAn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser_Id(Long id);
    @Query("select order from Order order where order.id=:order_id and order.user.id=:user_id")
    Order getOneOrder(Long order_id,Long user_id);
}
