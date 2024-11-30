package blackjack

import blackjack.domain.DeckBuilder.Companion.cachedDeck
import blackjack.domain.Players
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val names = inputView.inputUserNames()
    val players = Players(names)
    val cardGame = CardGame(cachedDeck, players)
    cardGame.dealCard()

    val initDto: Map<String, Map<List<String>, Int>> = cardGame.roundPlayers()
    ResultView.initRound(initDto)

    ResultView.roundRepeat(names, cardGame)

    val resultDto: Map<String, Map<List<String>, Int>> = cardGame.roundPlayers()
    ResultView.resultRound(resultDto)
}
