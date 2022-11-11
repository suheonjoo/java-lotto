package lotto.service;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.repository.UserLottoRepository;
import lotto.repository.dto.UserLottoDto;

public class LottoService {

	private final UserLottoRepository userLottoRepository;

	public LottoService(UserLottoRepository userLottoRepository) {
		this.userLottoRepository = userLottoRepository;
	}

	public UserLottoDto makeRandomLottoNumber(Integer buyPrice) {
		int lottoCount = Integer.valueOf(buyPrice) / 1000;
		for (int i = 0; i < lottoCount; i++) {
			List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
			userLottoRepository.updateLotto(new Lotto(numbers));
		}
		return userLottoRepository.findUserLottoDto();
	}

}
