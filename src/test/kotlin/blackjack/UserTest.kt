package blackjack

import blackjack.domain.Card
import blackjack.domain.Suit
import blackjack.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

class UserTest {
    @Test
    fun `유저는 이름을 가진다`() {
        val user = User("name")

        assertThat(user.name).isEqualTo("name")
    }

    @ParameterizedTest
    @EmptySource
    fun `이름은 공백일 수 없다`(value: String) {
        assertThatIllegalArgumentException().isThrownBy {
            User(value)
        }
    }

    @Test
    fun `카드를 받을 수 있다`() {
        val user = User("name")
        val card = Card("A", Suit.SPADE)

        user.receiveCard(card)

        assertThat(user.isBust()).isFalse()
    }

    @Test
    fun `Bust 상태를 알 수 있다`() {
        val user =
            User("name").apply {
                receiveCard(Card("J", Suit.SPADE))
                receiveCard(Card("J", Suit.SPADE))
                receiveCard(Card("J", Suit.SPADE))
            }

        assertThat(user.isBust()).isTrue()
    }
}
