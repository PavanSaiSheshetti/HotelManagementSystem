package com.revature.hms.repository;

import org.springframework.data.repository.CrudRepository;

import com.revature.hms.model.PickupAndDrop;


public interface PickupAndDropRepository extends CrudRepository<PickupAndDrop, Integer> {
  public PickupAndDrop findByPickupDropId(int pickupAndDropId);
}
