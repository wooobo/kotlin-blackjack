package blackjack

import blackjack.domain.*
import blackjack.ui.*

data class CardGame(private val deck: Deck, private val players: Players, val dealer: Dealer) {
    private val dealerShouldAddCard: Boolean
        get() = dealer.shouldAddCard

    fun startGame() {
        dealer.receive(deck.popCards(INITIAL_CARD_COUNT))
        players.dealInitialCards(deck, INITIAL_CARD_COUNT)
    }

    fun handleUserTurn(
        action: (Player) -> Boolean,
        printAction: (UserName, UserCards) -> Unit,
    ) {
        players.forEach { currentPlayer ->
            while (action(currentPlayer)) {
                players.deal(currentPlayer, deck.pop())
                printAction(currentPlayer.name, playerCards(currentPlayer.name))
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

    fun handlePlayerAmount(
        action: (
            userName: UserName
        ) -> Int
    ) {
        players.forEach { player ->
            player.bet(Money(action(player.name)))
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
