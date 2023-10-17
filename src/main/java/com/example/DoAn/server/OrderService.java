package com.example.DoAn.server;

import com.example.DoAn.entity.*;
import com.example.DoAn.repository.OrderDetailRepository;
import com.example.DoAn.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailsRepository;
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private UserService userService;
    public Order save(ShoppingCart shoppingCart){
        Order order=new Order();
        AtomicLong count = new AtomicLong(orderRepository.count());
        String maHang="KD"+ (count.incrementAndGet() + 1)+"TL";
        order.setMaHang(maHang);
        order.setOrderDate(new Date());
        order.setUser(shoppingCart.getUser());
        order.setTotalPrice(shoppingCart.getTotalPrice());
        order.setAccept(false);
        order.setOrderStatus("Pending");
        order.setQuantity(shoppingCart.getTotalItems());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        for(CartItem item:shoppingCart.getCartItems()){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(item.getProduct());
            orderDetail.setSize(item.getQuantity());
            orderDetailsRepository.save(orderDetail);
            orderDetailList.add(orderDetail);
        }
        order.setOrderDetailList(orderDetailList);
        cartService.deleteCartById(shoppingCart.getId());
        return orderRepository.save(order);
    }
    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public List<Order> findAllOrderForUser(Principal principal){
        User user= userService.findNameUser(principal.getName());
        return orderRepository.findAllByUser_Id(user.getId());
    }
    public Order findOne(Long order_id,Long user_id){
        return orderRepository.getOneOrder(order_id,user_id);
    }
    public Order findOne(Long id){
        return orderRepository.getById(id);
    }
    public void deleteOrder(Long id){
         orderRepository.deleteById(id);
    }
    public Order save(Order order){
        return orderRepository.save(order);
    }
}
