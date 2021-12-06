# 구현과정 상세 설명

## 1단계

<br>

**Revision 번호** : 19

<br>

구현 결과

![Step1](https://user-images.githubusercontent.com/81368630/144810359-96b78a4d-6bba-4cfc-8a39-f0e3400ea400.png)


현재 지도 데이터는 String에 2개의 Stage 데이터만 저장되어 있지만 여러 스테이지가 들어와도 stage 별로 맵 데이터를 저장 후 출력할 수 있도록 구현했습니다.

StageData Class를 만들어 mapdata를 `public static String`으로 선언하여 지도 데이터를 저장했습니다.

출력을 할 때 2차원 배열(map)에 `=`를 추가하지 않고도 Stage별로 출력 할 수 있을 것 같아 2차원 배열에는 Mapdata(벽, 구멍, 공, 플레이어, 빈칸)만 저장하도록 했습니다.

MapReader 클래스에서 지도 데이터를 읽은 후 각 Stage에 대한 정보를 가지고 있도록 했습니다.

Main 클래스에서 MapReader 객체를 만들어 Stage에 대한 정보를 PrintMap 클래스에 전달하여 출력하도록 했습니다.

<br>

### Main 클래스

MapReader 클래스에 있는 getStageSize()를 통해 지도 데이터에 존재하는 총 Stage의 개수를 얻어 와서 Stage 수 만큼 map데이터를 세팅 후 출력해주는 반복문을 실행합니다.

<br>

### MapReader 클래스

#### MapReader() 생성자

생성자를 통해 객체를 생성 시 `readMap()`을 통해 mapdata를 읽은 후 `countStage()`를 통해 mapdata의 stage시작 column을 저장합니다.

#### getStageSize() 메서드

`countStage()` 를 통해 저장된 stage 시작 시 column List인 `stageLine.size()` 를 통해 총 Stage 개수를 반환합니다.

#### getStages() 메서드

`setStartToLast(int stageNum)` 메서드를 통해 `stageLine`에서 stage 별 mapdata 유효 column을 계산합니다.

얻은 유효 column을 통해 배열의 height와 width를 `setHeightAndWidth(int height, int width)` 메서드로 스테이지 별로 저장합니다.

얻은 height와 width를 통해 2차원 int 배열 map을 생성 후 `initMap(int[][] map)` 을 통해 배열의 모든 값을 9로 초기 세팅합니다.

`setMap(int start, int last, int width, int[][] map, int stageNum)` 메서드를 통해 map에 stage 데이터를 저장합니다.

최종적으로 저장된 map 데이터를 return하여 Stage별 map 데이터를 반환하게 됩니다.

#### setStartToLast() 메서드

stage 별 유효 column을 계산한 후 반환합니다. 마지막 Stage일 경우 지도 데이터의 끝까지 확인하도록 설정했습니다.

#### setMap() 메서드

`classifyMap()`을 통해 데이터의 종류에 따라 map을 세팅합니다. #, O, o, P 에 따라 알맞은 int 값을 배정해주고 Player의 위치는 Position 객체를 통해 저장합니다.

map 세팅 과정 중 공의 개수와 구멍의 개수, 플레이어의 위치를 Stage별로 저장합니다.

<br>

#### 그 외 getter 메서드들

Stage 별로 Position, Hole, Ball, Width, Height 정보를 PrintMap Class에서 get 할 수 있도록 getter를 생성했습니다.

<br>

### PrintMap 클래스

요구하는 출력 형식에 맞게 출력하도록 했습니다.

mapReader 객체에 모든 정보가 저장되어 있으므로 매개변수로 mapReader와 Stage 지도 정보인 map, 스테이지 정보를 담고 있는 i를 선언했습니다. 

`print()`메서드를 `static`으로 선언해서 객체 생성 없이 언제든 Print 할 수 있도록 했습니다.

<br>

### Position 클래스

플레이어의 x, y 좌표를 가지고 있는 Position 클래스를 생성했습니다.

<br>

### StageData 클래스

지도 데이터를 가지고 있는 String이 선언되어 있는 클래스입니다.

`static`을 사용하여 객체 생성 없이 사용 할 수 있도록 했습니다.

<br>

## 2단계

<br>

**Revision 번호** : 29

<br>

### Main 클래스

Game 객체를 만들어 `run()` 한다.

<br>

### Game 클래스

MapReader 객체, UserInput 객체, MovePlayer 객체, Stage 2의 mapdata를 2차원 배열 `map`에 넣은 후 플레이어의 초기 위치를 저장한다.

이후 초기 map을 출력한 후 무한반복문을 통해 유저의 입력을 받고(`userInput.userInput()`) 유저의 명령어에 따라 동작(`movePlayer.move(map, commands, playerPosition)`)한다.


<br>

### UserInput 클래스

`userinput()` 메서드를 통해 입력받은 값(String)을 `char`배열로 바꿔 return 한다.


<br>

### MovePlayer 클래스

플레이어의 이동을 담당하는 클래스로 플레이어 이동과 map 출력기능을 하고 있다.

#### move() 메서드

`char[]`인 `commands`를 for each 문으로 하나의 `char`인 `command`로 반복문을 돌린다.

`playerPosition` 으로부터 플레이어의 현재 위치를 받은 후 `moveOnce()` 메서드를 호출한다.

#### moveOnce() 메서드

플레이어의 현재 위치, 현재 mapdata, 명령어를 받은 후 명령어에 따라 `moveWASD()` 메서드 또는 종료 혹은 존재하지 않는 명령어라는 명령을 실행한다.

#### moveWASD() 메서드

명령이 W, A, S, D 인경우 실행되며 이동이 불가능한 경우 경고메시지 출력 후 return하고 이동이 가능하면 `realMove()` 메서드를 호출한다.

이동 후에는 `printMapAndCommand()`를 통해 맵과 명령어를 출력한다.

#### realMove() 메서드

플레이어가 이동할 수 있는 경우 이동하도록 하는 메서드이다.

이동 한 후 playerPosition에 setter를 통해 player 위치를 재정의한다.

#### printMapAndCommand() 메서드

현재 mapdata를 출력한 후 입력받은 command와 경고문구 혹은 이동문구를 출력해주는 메서드이다.

<br>

### MapReader 클래스

1단계에서만 필요했던 기능을 삭제했다.(구멍, 공, 가로, 세로 등 저장기능 삭제)

그 외엔 1단계와 동일하다. (mapdata를 읽은 후 stage 정보(Player 위치, stage 별 map data)를 저장)

<br>

### Position 클래스

플레이어의 위치를 계속 변경하며 저장 할 필요가 생겨 setter를 생성했다.

<br>

### PrintMap 클래스

1단계와 달리 PrintMap은 2차원 배열 map만 조건에 맞춰 출력하면 되기 때문에 간소화 시켰다.

1단계 때와 마찬가지로 2중 for 문과 switch case로 map 값에 따라 알맞은 기호를 출력했다.

<br>

### StageData 클래스

1단계와 동일하다.(mapdata를 String으로 저장하고 있음)

## 3단계

## 4단계
