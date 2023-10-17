package com.example.DoAn.repository;

import com.example.DoAn.entity.CartItem;
import com.example.DoAn.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
