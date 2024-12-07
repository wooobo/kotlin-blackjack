package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.DeckBuilder
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.ui.DisplayCard
import blackjack.ui.DisplaySuit
import blackjack.ui.UserCards
import blackjack.ui.UserName

data class CardGame(private val deck: Deck, private val players: Players, val dealer: Dealer) {
    val dealerName: UserName
        get() = dealer.name
    private val dealerShouldAddCard: Boolean
        get() = dealer.shouldAddCard

    fun startGame() {
        dealer.receive(deck.popCards(INITIAL_CARD_COUNT))
        players.dealInitialCards(deck, INITIAL_CARD_COUNT)
    }

    fun handleUserTurn(
        action: (
            Player,
            UserCards,
        ) -> Boolean,
    ) {
        players.forEach { currentPlayer ->
            while (action(currentPlayer, playerCards(currentPlayer.name))) {
                players.deal(currentPlayer, deck.pop())
                if (currentPlayer.isBust) break
            }
        }
    }

    fun handleDealerTurn(action: () -> Unit) {
        if (dealerShouldAddCard) {
            action()
            dealCardToDealer()
        }
    }

    fun playerCards(name: String): UserCards {
        return players.findCardOf(name).values().map { DisplayCard(it.rank.name, DisplaySuit.valueOf(it.suit.name)) }
    }

    fun resultEvaluator(): GameResultEvaluator {
        return GameResultEvaluator(players, dealer)
    }

    fun allPlayersNames(): List<UserName> {
        return players.names()
    }

    private fun dealCardToDealer() {
        dealer.receive(Deck(listOf(deck.pop())))
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2

        fun create(users: List<String>): CardGame {
            return CardGame(DeckBuilder.cachedDeck, Players.from(users), Dealer())
        }

        fun from(
            deck: Deck,
            users: List<String>,
        ): CardGame {
            return CardGame(deck, Players.from(users), Dealer())
        }
    }
}
