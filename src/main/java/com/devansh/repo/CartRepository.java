package com.devansh.repo;

import com.devansh.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByCustomerId(Integer userId);


}
