package com.customer.rewards.service.impl;


import com.customer.rewards.entity.Transaction;
import com.customer.rewards.model.RewardDetails;
import com.customer.rewards.repository.TransactionRepository;
import com.customer.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardsServiceImpl implements RewardsService {

	
	@Autowired
	TransactionRepository transactionRepository;

	public static final int noOfDaysInMonth = 30;
	public static int rewardRange1 = 50;
	public static int rewardRange2 = 100;

	public RewardDetails getRewardsOfCustomer(Long customerId) {

		Timestamp lastMonthTimestamp = getDateBasedOnDiffDays(noOfDaysInMonth);
		Timestamp lastSecondMonthTimestamp = getDateBasedOnDiffDays(2* noOfDaysInMonth);
		Timestamp lastThirdMonthTimestamp = getDateBasedOnDiffDays(3* noOfDaysInMonth);

		List<Transaction> lastMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(
				customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));

		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp,
						lastMonthTimestamp);

		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
						lastSecondMonthTimestamp);

		Long lastMonthRewardPoints = getRewardsForTheMonth(lastMonthTransactions);
		Long lastSecondMonthRewardPoints = getRewardsForTheMonth(lastSecondMonthTransactions);
		Long lastThirdMonthRewardPoints = getRewardsForTheMonth(lastThirdMonthTransactions);

		RewardDetails customerRewards = new RewardDetails();
		customerRewards.setCustomerId(customerId);
		customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
		customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
		customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
		customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

		return customerRewards;

	}

	private Long getRewardsForTheMonth(List<Transaction> transactions) {
		return transactions.stream().map(this::calculateRewards).mapToLong(r -> r).sum();
	}

	private Long calculateRewards(Transaction t) {
		if (t.getTransactionAmount() > rewardRange1 && t.getTransactionAmount() <= rewardRange2) {
			return Math.round(t.getTransactionAmount() - rewardRange1);
		} else if (t.getTransactionAmount() > rewardRange2) {
			return Math.round(t.getTransactionAmount() - rewardRange2) * 2
					+ (rewardRange2 - rewardRange1);
		} else
			return 0l;

	}

	public Timestamp getDateBasedOnDiffDays(int days) {

		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}

}
