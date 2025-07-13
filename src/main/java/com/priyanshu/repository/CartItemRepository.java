package com.priyanshu.repository;

import com.priyanshu.entity.CartItem;
import com.priyanshu.entity.Product;
import com.priyanshu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByUserAndProduct(User user, Product product);

    Optional<List<CartItem>> findByUser(User user);
}
