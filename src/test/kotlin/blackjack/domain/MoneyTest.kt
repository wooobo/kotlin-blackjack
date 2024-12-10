package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `플레이어는 베팅 금액을 설정할 수 있다`() {
        val player = Player.from("철수")
        val bettingMoney = Money(1000)

        player.bet(bettingMoney)

        player.bettingAmount shouldBe bettingMoney
    }

    @Test
    fun `블랙잭이면 베팅 금액의 더 획득한다`() {
        val player = Player.from("철수")
        player.bet(Money(1000))

        val actual = player.evenMoney()

        actual shouldBe Money(1500)
    }
}
