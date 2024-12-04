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
- CardRank
    - [X] Ace, Face, Number 타입을 가진다
    - [X] Ace는 1 또는 11로 계산할 수 있다
    - [X] Face는 10으로 계산할 수 있다
    - [X] Number는 2~10까지의 숫자로 계산할 수 있다
- Card
    - [X] `CardRank`, `CardSuit`를 가진다
    - [X] `CardRank`의 Score 알 수 있다
    - [X] 이름을 알 수 있다
    - [X] `score` 알 수 있다
- Hand
    - [X] `Card`를 추가할 수 있다
    - [X] `Score`를 계산한다
    - [X] `Bust` 상태를 알 수 있다
    - [X] 가지고 있는 `Card`를 알 수 있다
- Name
    - [X] 공백 일 수 없다
- Player
    - [X] `Name`을 가진다
    - [X] `Card`를 받을 수 있다
    - [X] `Bust` 상태를 알 수 있다
        - `Bust`는 카드 숫자를 합쳐 21을 초과하는지 알 수 있다.
- Players
    - [X] `Player`를 가진다
    - [X] `Player`에게 카드를 나눠 줄 수 있다
- Deck
    - [X] `Card`를 가진다
    - [X] `Card`를 하나씩 꺼낼 수 있다
    - [X] `Card`를 꺼낼때 카드가 없으면 예외 발생 한다
    - [X] `Card`를 갯수만큼 꺼낼 수 있다
    - [X] `Card` 지정 번째 꺼낼 수 있다
    - [ ] `Card`를 섞을 수 있다
- CardGame
  - [X] 사용자 이름을 받는다 
  - [X] 두 장의 카드를 가지고 시작한다
  - [X] 지정 유저의 카드를 추가한다
  - [X] 유저 카드를 알 수 있다

## 2단계 피드백
- [X] startCardCount 상수화
- [X] Card 정적팩토리 메소드 활용
- [X] toString 활용
- [X] CardRank enum을 활용
- [X] CardBuilder 는 필요한가?
  - 필요 없어서 삭제
- [X] Name 과한 일반화
- [X] `Suit` display name 제거 
## 2단계 피드백 (2)
- [X] CardRank.symbol 추상화

# 3단계 - 블랙잭(딜러)

## 추가 요구사항
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

## 구현 상세
- `Dealer`
  - `16점` 이하일 경우 카드를 추가로 받는다.
  - `17점` 이상일 경우 더 이상 카드를 받지 않는다.
- `Dealer`가 `Bust` 상태가 되면 남아있는 플레이어는 자동 승리.
- `각 플레이어`와 `딜러`의 `점수`를 비교하여 `승패를 출력`.

## 기능 구현
- Dealer
  - [X] `Player`를 상속 받는다
  - [X] 16점 이하일 경우 카드를 추가로 받는다
  - [X] 17점 이상일 경우 카드를 추가로 받지 않는다
  - [ ] Bust 상태 시 남아있는 모든 플레이어를 승리
- PlayerName
  - [ ] 일반 플레이어는 `Dealer`와 이름이 같을 수 없다. 
- [X] 게임을 시작하면 딜러와 유저들은 카드를 받는다