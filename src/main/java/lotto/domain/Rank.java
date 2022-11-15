package lotto.domain;

import java.util.Arrays;

import lotto.util.RankConst;

public enum Rank {
	fifthRank(5_000, RankConst.FIFTH_RANK_RESULT),
	forthRank(50_000, RankConst.FORTH_RANK_RESULT),
	thirdRank(1_500_000, RankConst.THIRD_RANK_RESULT),
	secondRank(30_000_000, RankConst.SECOND_RANK_RESULT),
	firstRank(2_000_000_000, RankConst.FIRST_RANK_RESULT);

	private final Integer price;
	private final String rankCountDescription;
	private Integer count;

	Rank(int price, String rankCountDescription) {
		this.price = price;
		this.rankCountDescription = rankCountDescription;
		this.count = 0;
	}

	public static double findYield(Integer buyPrice) {
		double sum = Arrays.stream(Rank.values())
			.mapToDouble(i -> i.price * i.count)
			.reduce(0, (total, y) -> total + y);
		return Math.round(sum * 1000d / (double)buyPrice) / 10d;
	}

	public void updateCount(Integer number) {
		this.count += number;
	}

	public String getUserStatisticsResult() {
		return this.rankCountDescription + this.count + RankConst.COUNT;
	}

	public static void clearCount() {
		Arrays.stream(Rank.values()).forEach(rank -> rank.count = 0);
	}
}
