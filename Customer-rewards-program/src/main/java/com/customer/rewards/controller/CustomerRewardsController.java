package com.customer.rewards.controller;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.exception.ResourceNotFoundException;
import com.customer.rewards.model.RewardDetails;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.service.RewardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Tag(
        name = "Customer Reward Calculator controller"
)
public class CustomerRewardsController {
    @Autowired
    RewardsService rewardsService;

    @Autowired
    CustomerRepository customerRepository;

    Logger logger = LoggerFactory.getLogger(CustomerRewardsController.class);

    //http://localhost:8080/customerReward/rewardPoints/100

    @Operation(
            summary = "Calculating rewards",
            description = "Calculating reward points for past 3 months based on customer id"
    )
    @GetMapping(value = "/rewardPoints/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RewardDetails> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        logger.info("Calculating rewards for customer :: "+ customerId);
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
            logger.error("Customer id :: " + customerId +" not found.");
            throw new ResourceNotFoundException("Customer", "Id", customerId);
        }
        RewardDetails customerRewards = rewardsService.getRewardsOfCustomer(customerId);
        logger.debug("Rewards for customer :: " +customerRewards.getCustomerId()
                +" = " +customerRewards.getTotalRewards());
        return new ResponseEntity<>(customerRewards, HttpStatus.OK);
    }

}
