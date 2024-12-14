package com.kp.arteon.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orderpayment")
public class OrderController {

    private RazorpayClient razorpayClient;

    public OrderController() throws Exception {
        // Replace with your actual Key ID and Secret
        razorpayClient = new RazorpayClient("rzp_test_P8uE7TxSe8Yt9B", "5uEVDHTY8cxTYLeCSdVb3wRE");
    }

    @PostMapping("/create-order")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();
        try {
            int amount = (int) data.get("amount"); // amount in paise (so 500 = ₹5.00)
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // Convert to paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_123456");

            Order order = razorpayClient.orders.create(orderRequest);
            response.put("orderId", order.get("id"));
            response.put("status", "success");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
