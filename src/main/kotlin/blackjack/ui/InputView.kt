package blackjack.ui

class InputView(val inputProvider: () -> String? = { readln() }) {
    fun inputUserNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return inputProvider()?.split(",") ?: throw IllegalArgumentException("입력이 없습니다.")
    }
}
