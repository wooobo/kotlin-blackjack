# kotlin-blackjack
# 프로그래밍 요구 사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

# 1단계 - 코틀린 DSL
- [X] DslTest 완성

## 1단계 피드백
- data class 는 value class 와 어떤 차이가 있는지 알아보기
- kotest 를 사용해보기

# 2단계 - 블랙잭
기능 요구사항
> 블랙잭 게임을 변형한 프로그램을 구현한다. 
> 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 
21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

## 기능 구현
- [ ] 카드가 존재한다.
  - [ ] 카드는 52장이다.
  - [ ] 카드는 2~10, J, Q, K, A로 구성된다.
  - [ ] A는 1 또는 11로 계산할 수 있다.
- CardRank
  - [ ] Ace, Face, Number 타입을 가진다
  - [ ] Ace는 1 또는 11로 계산할 수 있다
  - [ ] Face는 10으로 계산할 수 있다
  - [ ] Number는 2~10까지의 숫자로 계산할 수 있다
- Card
  - [ ] `CardRank`, `CardSuit`를 가진다
  - [ ] `CardRank`의 Score 알 수 있다
  - [ ] 이름을 알 수 있다
- Hand
  - [ ] `Card`를 추가할 수 있다
  - [ ] `Score`를 계산한다
  - [ ] `Bust` 상태를 알 수 있다 ????
  - [ ] 가지고 있는 `Card`를 알 수 있다
- Name
  - [ ] 공백 일 수 없다
- Player
  - [ ] `Name`을 가진다 
  - [ ] `Card`를 받을 수 있다
  - [ ] `Bust` 상태를 알 수 있다
    - `Bust`는 카드 숫자를 합쳐 21을 초과하는지 알 수 있다.
- Players
  - [ ] `Player`를 가진다
  - [ ] `Player`에게 카드를 나눠 줄 수 있다
- Deck
  - [ ] `Card`를 가진다
  - [ ] `Card`를 하나씩 꺼낼 수 있다
  - [ ] `Card`를 섞을 수 있다
  - [ ] `Card`를 꺼낼때 카드가 없으면 예외 발생 한다
- [ ] csv 로 사용자 이름을 받는다.
- [ ] 두 장의 카드를 가지고 시작한다.
