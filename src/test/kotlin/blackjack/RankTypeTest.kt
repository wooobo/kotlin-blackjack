package blackjack

import blackjack.domain.RankType
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RankTypeTest() {
    @ParameterizedTest
    @ValueSource(strings = ["2", "3", "4", "5", "6", "7", "8", "9", "A", "J", "Q", "K"])
    fun `RankType은 2~10, J, Q, K, A로 구성된다`(value: String) {
        RankType.from(value)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 10])
    fun `Number 타입은 범위가 존재한다`(number: Int) {
        assertThatIllegalArgumentException().isThrownBy {
            RankType.Number(number)
        }
    }
}
