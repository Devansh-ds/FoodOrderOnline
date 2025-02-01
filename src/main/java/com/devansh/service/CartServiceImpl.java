package com.devansh.service;

import com.devansh.Model.*;
import com.devansh.repo.CartItemRepository;
import com.devansh.repo.CartRepository;
import com.devansh.repo.UserRepository;
import com.devansh.request.AddCartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest request, User user) throws Exception {
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Food food = foodService.findFoodById(request.getFoodId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)) {
                Integer newQuantity = cartItem.getQuantity() + request.getQuantity();
                cartItem.setQuantity(newQuantity);
                cartItem.setIngredients(request.getIngredients());
                
                CartItem updatedItem = updateCartItemQuantity(cartItem.getId(), newQuantity);
                BigDecimal newTotal = calculateCartTotal(cart);
                cart.setTotal(newTotal);
                cartRepository.save(cart);
                return updatedItem;
            }
        }

        BigDecimal totalAmount = BigDecimal.valueOf(request.getQuantity() * food.getPrice().doubleValue());

        CartItem cartItem = CartItem
                .builder()
                .cart(cart)
                .food(food)
                .ingredients(request.getIngredients())
                .quantity(request.getQuantity())
                .totalPrice(totalAmount)
                .build();
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        cart.getItems().add(savedCartItem);
        BigDecimal newTotal = calculateCartTotal(cart);
        cart.setTotal(newTotal);

        cartRepository.save(cart);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Integer cartItemId, Integer newQuantity) throws Exception {
        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(() -> new Exception("CartItem not found with id: " + cartItemId));
        cartItem.setQuantity(newQuantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice().multiply(new BigDecimal(newQuantity)));

        var cart = cartItem.getCart();
        var newTotal = calculateCartTotal(cart);
        cart.setTotal(newTotal);
        cartRepository.save(cart);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Integer cartItemId, User user) throws Exception {
        Cart cart = cartRepository.findByCustomerId(user.getId());

        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(() -> new Exception("CartItem not found with id: " + cartItemId));

        cart.getItems().remove(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public BigDecimal calculateCartTotal(Cart cart) throws Exception {
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        for (CartItem cartItem : cart.getItems()) {
            totalAmount = totalAmount.add(cartItem.getTotalPrice());
        }
        return totalAmount;
    }

    @Override
    public Cart findCartById(Integer cartId) throws Exception {
        return cartRepository
                .findById(cartId)
                .orElseThrow(() -> new Exception("Cart not found with id: " + cartId));
    }

    @Override
    public Cart findCartByUserId(Integer userId) throws Exception {
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Integer userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.getItems().clear();
        cart.setTotal(BigDecimal.valueOf(0));
        return cartRepository.save(cart);
    }
}
