package com.devansh.controller;

import com.devansh.Model.Cart;
import com.devansh.Model.CartItem;
import com.devansh.Model.User;
import com.devansh.request.AddCartItemRequest;
import com.devansh.request.UpdateCartItemRequest;
import com.devansh.service.CartService;
import com.devansh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request,
                                                  @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(cartService.addItemToCart(request, user));
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest request,
                                                           @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity()));
    }

    @DeleteMapping("/cart-item/{cartItemId}")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Integer cartItemId,
                                               @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(cartService.removeItemFromCart(cartItemId, user));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> findCartById(@PathVariable Integer cartId,
                                             @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(cartService.findCartById(cartId));
    }

    @GetMapping
    public ResponseEntity<Cart> findCartItems(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(cartService.findCartByUserId(user.getId()));
    }

    @PutMapping("/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findByJwtToken(token);
        return ResponseEntity.ok(cartService.clearCart(user.getId()));
    }

}























