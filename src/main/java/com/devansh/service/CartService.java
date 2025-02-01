package com.devansh.service;

import com.devansh.Model.Cart;
import com.devansh.Model.CartItem;
import com.devansh.Model.User;
import com.devansh.request.AddCartItemRequest;

import java.math.BigDecimal;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest request, User user) throws Exception;

    public CartItem updateCartItemQuantity(Integer cartItemId, Integer quantity) throws Exception;

    public Cart removeItemFromCart(Integer cartItemId, User user) throws Exception;

    public BigDecimal calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Integer cartItemId) throws Exception;

    public Cart findCartByUserId(Integer userId) throws Exception;

    public Cart clearCart(Integer userId) throws Exception;



}
