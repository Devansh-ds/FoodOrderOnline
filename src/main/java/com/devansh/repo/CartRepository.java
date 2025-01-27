package com.devansh.repo;

import com.devansh.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByCustomerId(Integer id);

}
