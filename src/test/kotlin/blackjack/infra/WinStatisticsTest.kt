package blackjack.infra

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WinStatisticsTest {
    @Test
    fun `win lose draw 를 설정 할 수 있다`() {
        val winStatisticsBuilder = WinStatisticsBuilder()

        winStatisticsBuilder.onWin()
        winStatisticsBuilder.onWin()
        winStatisticsBuilder.onLose()
        winStatisticsBuilder.onDraw()

        val actual = winStatisticsBuilder.build()
        assertAll(
            { assert(actual.wins == 2) },
            { assert(actual.losses == 1) },
            { assert(actual.draws == 1) }
        )
    }

}