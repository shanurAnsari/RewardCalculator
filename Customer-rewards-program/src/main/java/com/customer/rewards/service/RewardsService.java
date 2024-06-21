package com.customer.rewards.service;

import com.customer.rewards.model.RewardDetails;

public interface RewardsService {
    public RewardDetails getRewardsOfCustomer(Long customerId);
}
