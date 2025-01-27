package com.devansh.repo;

import com.devansh.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.Inet4Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
