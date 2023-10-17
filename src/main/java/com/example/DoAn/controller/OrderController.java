package com.example.DoAn.controller;

import com.example.DoAn.dto.UserDto;
import com.example.DoAn.entity.Order;
import com.example.DoAn.entity.OrderDetail;
import com.example.DoAn.entity.ShoppingCart;
import com.example.DoAn.entity.User;
import com.example.DoAn.server.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class OrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryServer categoryServer;
    @GetMapping("/user/abate")
    public String abate(Model model, Principal principal){
        if(principal==null){
            return "redirect:/login";
        }
        User user= userService.findNameUser(principal.getName());
        String fullName=user.getFirstName()+" "+user.getLastName();
        ShoppingCart shoppingCart=user.getCart();
        model.addAttribute("user", user);
        model.addAttribute("fullName", fullName);
        model.addAttribute("shoppingCart",shoppingCart);
        return "order";
    }

    @GetMapping("/user/order")
    public String order(Model model,Principal principal){
        if(principal==null){
            return "redirect:/login";
        }
        User user= userService.findNameUser(principal.getName());
        String fullName=user.getFirstName()+" "+user.getLastName();
        ShoppingCart shoppingCart=user.getCart();
        model.addAttribute("user", user);
        model.addAttribute("fullName", fullName);
        model.addAttribute("orderList",orderService.findAllOrderForUser(principal));
        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("displayText", "ĐƠN HÀNG");
        return "donhang";
    }
    @PostMapping("/user/save_order")
    public String saveAddress(Principal principal, @ModelAttribute UserDto userDto){
        User customer = userService.findNameUser(principal.getName());
        if(customer!=null){
            userService.saveUserAddress(userDto,principal);
        }
        ShoppingCart cart = customer.getCart();
        Order order = orderService.save(cart);

        return "redirect:/shop/user/order";
    }
    @GetMapping("/user/orderDetail/{id}")
    public String orderDetail(@PathVariable("id")Long id,Principal principal,Model model){
        if(principal==null){
            return "redirect:/login";
        }
        Long user_id;
        User user= userService.findNameUser(principal.getName());
        user_id=user.getId();
        Order order=orderService.findOne(id,user_id);
        List<OrderDetail> orderDetailList=order.getOrderDetailList();
        String fullName=user.getFirstName()+" "+user.getLastName();
        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("fullName",fullName);
        model.addAttribute("user",user);
        model.addAttribute("orderDetail",orderDetailList);
        model.addAttribute("order",order);
        model.addAttribute("displayText", orderService.findOne(id).getMaHang());
        return "chitietdonhang";
    }
    @GetMapping("/order/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return "redirect:/shop/user/order";
    }
}
