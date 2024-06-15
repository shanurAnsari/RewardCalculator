package com.customer.rewards.controller;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.exception.ResourceNotFoundException;
import com.customer.rewards.model.RewardDetails;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerReward")
public class CustomerRewardsController {
    @Autowired
    RewardsService rewardsService;

    @Autowired
    CustomerRepository customerRepository;

    //http://localhost:8080/customerReward/rewardPoints/100

    @GetMapping(value = "/rewardPoints/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RewardDetails> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
            throw new ResourceNotFoundException("Customer", "Id", customerId);
        }
        RewardDetails customerRewards = rewardsService.getRewardsOfCustomer(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }

}
