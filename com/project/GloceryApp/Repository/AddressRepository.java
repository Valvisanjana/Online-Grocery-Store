package com.project.GloceryApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.GloceryApp.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
