# 구현과정 상세 설명

<br>

**코드 실행 방법**

모든 단계는 Main.java 클래스에 메인 메서드가 있습니다.

`javac Main.java -encoding utf-8`

`java -Dfile.encoding=UTF-8 Main`

을 통해 단계별 코드를 실행 할 수 있습니다.

<br>

## 1단계

<br>

**Revision 번호** : 19     // hash값 :  ff16562

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

**Revision 번호** : 29   // hash값 :  b8d98f0

구현결과

![stage2](https://user-images.githubusercontent.com/81368630/145153837-4c3d6bae-e2ee-498c-b66d-2026d7fe1ec1.png)


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

**Revision 번호** : 52 // hash 값 : 88b763a

<br>

Stage 별 정답

| Stage | 정답 | 명령 횟수 |
|--|--|--|
| 1| a | 1|
| 2|wsswddaaaa | 10|
| 3|sawdwawaasassdwddwdswawaasddaaassdwawdd |39 |
| 4|dassdawwdsassdddwwwadsssaaawwdsdsa | 34|
| 5|wawwdsdswaaaaassddwdwddswawaaasdwddsdsaadwwaasdaaassddwdwasdddwaa | 65|


<br>


구현 결과

![3단계](https://user-images.githubusercontent.com/81368630/145021231-d70c4b2d-62ca-48b6-9bf3-1c59fcba860a.gif)



### 구현방법

|기호 | 의미 | 저장값 |
|--|--|--|
|   | 빈칸| 0 |
| O |구멍 | 1 |
| o  | 공 | 2 |
| 0(mapdata) ⊙(출력 때만)  | 구멍 + 공 | 3 |
| P  | 플레이어 | 4 |
| P|플레이어 + 구멍  /이하(플+구)| 5 |
| # | 벽 | 9|

<br>
이동 구현을 편리하게 하기 위해서 위와 같이 기호에 따라 저장값을 다뤘습니다.

자세한 내용은 `MovePlayer` 클래스에서 다루겠습니다.

<br>

큰 그림만 간략히 짚은 후 자세한 내용은 클래스 설명을 통해 하겠습니다.

Main 클래스에서 Game 클래스 객체 생성 후 게임을 실행합니다.

Game 클래스는 생성자를 통해 `init()` 메서드를 통해 초기화한 후 `run()`메서드를 통해 게임 세팅과 출력 및 게임을 실행합니다.

`init()` 메서드는 게임 구동에 필요한 객체를 생성합니다.

`run()` 메서드는 정보 출력, Stage 개수만큼 반복문을 통해 게임을 실행합니다.

`run()`을 통해 게임 실행 시 `사용자 입력 -> 명령어 판단 -> 유효 명령일 시 이동 or 종료 or 리셋 실행 -> Map 프린트 -> 정답확인` 과정을 반복합니다.

<br>  

###  Main 클래스

game 객체 생성 후 `run()` 메서드를 호출해 게임을 시작합니다.

<br>

### Game 클래스

클래스 변수 : `nowStage` (현재 진행중인 Stage 단계 값 저장) (Stage 리셋 시 필요함)

인스턴스 변수 : `mapReader, userInput, playerPosition, classification`

#### 생성자
`init()` 메서드를 호출합니다.

#### init()
`mapReader, userInput, movePlayer, classification` 객체를 생성합니다.

#### run()
Print 클래스의 static 메서드 `printStartMessage(), stageEndPrint(), gameEndPrint(), printMap()` 을 통해 적절한 때에 필요한 정보를 출력합니다.

총 Stage 개수를 mapReader로부터 읽어와 Stage 수만큼 반복문을 통해 Stage별 게임을 실행합니다.

반복문 안에는 Stage 시작 시 초기세팅하는 `initSetting()` 메서드와 정답을 맞추거나 Q명령을 통해 종료되기 전까지 게임을 진행하는 `playGame()`메서드, 출력메서드가 있습니다.

마지막으로 userInput 객체의 Scanner를 close()하는 메서드를 호출합니다.

#### initSetting()
Stage 정보를 초기 세팅하는 메서드입니다.

숫자로 지도 데이터를 가지고 있는 map, player의 위치 정보를 가지고 있는 playerPosition, 이동 시 카운트 초기화, 현재 스테이지를 nowStage에 저장, 스테이지별 map 데이터를 CopyMap 데이터에 저장합니다.

#### playGame()

Stage별 게임을 진행하는 메서드입니다. 정답을 맞출 때까지 반복됩니다.

`userInput.userInput()`을 통해 사용자 입력을 받고

`classification.performCommands()`를 통해 입력이 유효하면 이동 or 종료 or 리셋을 입력 후 map을 출력하고 입력이 유효하지 않다면 경고문구를 출력하도록 합니다.

`checkEndGame()`메서드를 통해 map이 정답인지 확인합니다.


#### checkEndGame(), checkMapEndGame()

map이 정답인지 확인하는 메서드입니다. 정답일 시 `playGame()`메서드의 반복문이 종료되어 현재 Stage가 종료됩니다.

<br>

### MapReader 클래스

2단계 MapReader와 거의 유사합니다. (mapdata를 읽은 후 stage 정보(Player 위치, stage 시작 column)를 저장)

Stage reset을 구현하기 위한 Player의 초기 위치를 저장하는 `initPosition`을 만들었습니다.

생성자에 `readMapFile()`메서드가 변경되었으며 다른 클래스의 String을 읽는 것이 아닌 `map.txt`파일을 읽어와 mapdata를 읽어옵니다.

### 생성자

`readMapFile()` 메서드로 `map.txt`파일로부터 지도 데이터 읽어옵니다.

`countStage()` 메서드로 Stage 별 시작 column 저장합니다.

### readMapfile()

map.txt 로부터 지도 데이터를 읽어옵니다.

오류 시 오류메시지 출력합니다.

### countStage()

지도 데이터를 통해 Stage 별 시작 column을 `stageLine`에 저장합니다.

### getStageSize()

스테이지의 총 개수(총 Stage 라운드)를 반환합니다.

### getStages()

매개변수로 Stage 단계를 받아서 그 단계에 맞는 map 정보를 return합니다.

### setStartToLast()

처음 읽을 column과 마지막 column을 반환합니다.

### setMap()

map을 지도 데이터에 맞게 세팅하는 메서드입니다.

### classifyMap()

지도 데이터를 구분하여 map에 알맞은 값을 배정합니다.

P를 찾으면 Player 위치 정보를 PlayerPosition과 reset시 사용할 initPosition에 저장합니다.

### getter

이동을 구현하기 위해 플레이어의 위치 정보를 getter로 만들었습니다.

reset을 구현하기 위해 플레이어의 초기 위치 정보를 getter로 만들었습니다.

<br>

### Position 클래스

플레이어의 위치 정보를 가지고 있는 클래스입니다.

플레이어의 위치는 한번에 set 하기 때문에 `setXY()` 메서드를 통해 x, y 좌표를 한번에 set 합니다.

<br>

### MovePlayer 클래스

|기호 | 의미 | 저장값 |
|--|--|--|
|   | 빈칸| 0 |
| O |구멍 | 1 |
| o  | 공 | 2 |
| 0(mapdata) ⊙(출력 때만)  | 구멍 + 공 | 3 |
| P  | 플레이어 | 4 |
| P|플레이어 + 구멍  /이하(플+구)| 5 |
| # | 벽 | 9|

**위의 설정으로 한 이유**
(플레이어 현재 위치 (x, y), 움직일 위치 (x + a, y + b), 같은방향 2칸 뒤 (x+2*a,y+2*b))
1. map 기본설정이 필요 없어짐 (default 값이 0이라 빈칸이 default 설정으로 됨)
2. 이동 & 공밀기 구현이 편해짐
    1. 못 움직이는 상황 (return)
    2. 공 미는 상황
        - moveBall() 메서드 -> (x,y)값 4 뺌, (x+a,y+b)값 2더함, (x+2*a,y+2*b)값 2 더함
    3. 그냥 플레이어만 움직이는 상황 (realMove() 메서드)
        - realMove() 메서드 -> (x,y)값 4 뺌, (x+a,y+b)값 4더함

**정리한 로직**
![iOS 이미지](https://user-images.githubusercontent.com/81368630/145030604-2e9b0d4c-129c-4209-a481-99e9ace94220.jpg)

#### moveWASD()

w, a, s, d 입력이 들어왔을 때 실행되는 메서드입니다.

위 정리처럼 1. 못 움직이는 상황에서는 return / 2. 공 미는 상황은 moveBall() 호출 / 3. 그냥 이동하는 상황에서 reaMove() 호출을 하고 있습니다.

#### moveBall()
플레이어가 공을 움직이는 로직입니다.

플레이어 현재 위치에 4를 빼고, 플레이어가 이동할 위치에 2를 더하고 2칸 뒤의 위치에 2를 더해줍니다.

이후 움직인 위치를 playerPosition에 저장합니다.

#### realMove()
플레이어만 움직이는 로직입니다.

플레이어 현재 위치에 4를 빼고 플레이어가 이동할 위치에 4를 더해줍니다. 이후 움직인 위치를 playerPosition에 저장합니다.

#### PrintMapAndCommand()

map 데이터 출력과 이동문구를 출력합니다.


<br>

### UserInput 클래스
Scanner를 통해 사용자의 input을 받은 후 char[] 로 반환합니다.

2단계와 동일합니다.


<br>

### Print 클래스

map data, 게임시작시, 각 스테이지 클리어시, 모든 스테이지 클리어시 출력하는 메서드를 가지고 있습니다.


<br>

### CopyMap 클래스

Stage마다 map을 copy해서 stageMap에 저장한 후에 reset을 위해 저장된 stageMap을 map으로 copy 하는 역할을 합니다.

#### copyInitialMap()

초기 map 상태를 stageMap에 저장하는 메서드입니다.

#### copyMap()

reset 시 map을 초기 stageMap 상태로 변환해주는 기능을 하는 메서드입니다.

<br>

### Classification 클래스

사용자의 여러 입력값을 하나씩 순차적으로 실행합니다.

유효한 명령어(이동, 종료, 리셋)면 명령을 수행하는 메서드를 호출합니다.

이동 명령어 시 count를 1 증가해서 Stage 종료 시 이동 횟수를 출력할 수 있도록 합니다.

#### 생성자
movePlayer와 mapReader를 생성자로 생성합니다.

#### performCommands()

여러개의 사용자 입력값을 하나씩 수행합니다.

#### validateCommand()

이동, 종료, 리셋 명령어 시 알맞은 메서드를 호출하고 유효하지 않은 명령 시 현재 지도와 경고문구를 출력합니다.

#### moveCommand()
명령이 w a s d 인지 확인하고 맞을 시 이동하는 메서드를 호출합니다.

#### moveAndCount()
이동하는 메서드 호출 후 count를 1 증가하여 이동 횟수를 체크합니다.

#### resetGame()
R 입력 시 실행되며 Stage를 초기화합니다.

count 초기화, playerPosition 초기화, map 초기화 한 후 초기 상태의 map을 출력합니다.

#### 게터, 세터

이동 횟수 출력 및 초기화할 수 있도록 count 변수에 접근 할 수 있는 게터와 세터를 설정했습니다.

  <br>

## 4단계

<br>

**Revision 번호**: 79

<br>

Stage 별 정답

| Stage | 정답 | 명령 횟수 |
|--|--|--|
| 1| a | 1|
| 2|wsswddaaaa | 10|
| 3|sawdwawaasassdwddwdswawaasddaaassdwawdd |39 |
| 4|dassdawwdsassdddwwwadsssaaawwdsdsa | 34|
| 5|wawwdsdswaaaaassddwdwddswawaaasdwddsdsaadwwaasdaaassddwdwasdddwaa | 65|

<br>

저장하기 불러오기 기능은 모두 구현 하지 못했습니다. 지도 데이터 변환 프로그램과 되돌리기 기능 및 되돌리기 취소 기능은 구현했습니다.

저장하기 불러오기 기능 중 입력에 따른 명령 분리 부분은 구현했습니다. (1S, 2S 를 입력 받고 입력에 따라 분류하는 것까지)

사용자의 입력 명령에 따라 저장하기, 불러오기 기능을 구현하지 못했습니다.

<br>

구현결과

변환된 지도 데이터 일부분

map_enc.txt 파일을 확인하면 바이너리로 저장하여 사람은 정보를 읽을 수 없도록 했다.

![map_enc](https://user-images.githubusercontent.com/81368630/145155085-22735ba7-1983-4d82-9f35-21b8cae30c17.jpg)

<br>

되돌리기 및 되돌리기 취소 기능, 2S, 4L 입력 기능

![1](https://user-images.githubusercontent.com/81368630/145156873-f588d796-fb19-4f2a-b100-300e015120df.jpg)

![2](https://user-images.githubusercontent.com/81368630/145156877-889032ba-e20f-44e8-bd61-abc37fdf3247.jpg)

![3](https://user-images.githubusercontent.com/81368630/145156879-7fc32bba-c4ce-4d4a-98e5-33f5a8df5ede.jpg)

![4](https://user-images.githubusercontent.com/81368630/145173486-c8bbf2ec-6de0-46a8-892b-641d4a9d3e55.jpg)



<br>


### 지도 데이터 변환하기 프로그램

지도 데이터를 바이너리 코드로 암호화한 후 map_enc.txt에 저장합니다.

소코반 게임을 실행하면 MapReader 클래스에서 Decode 클래스를 통해 map_enc.txt를 복호화하여 지도 데이터를 읽어서 게임을 실행합니다.

#### EncryptMain

암호화 하는 프로그램의 메인메서드를 가지고 있는 메인클래스입니다. Encrypt 클래스에 map.txt 파일을 넘겨준 후 암호화되어져서 나온 String을 새로운 map_enc.txt에 저장합니다.

#### Encrypt

"map.txt"를 입력받아 바이너리 코드로 암호화 한 후 String으로 반환합니다.

"map.txt" 파일의 정보를 `ByteArrayOutputStream` 클래스의 `toByteArray()` 메서드를 이용해 byte 배열을 생성하였고

생성한 byte 배열을 java.util.Base64.Encoder 의 `encode()` 메서드를 이용해서 바이너리 데이터로 변환했습니다.

이후 바이너리 데이터를 하나의 문자열로 만들어 반환합니다.

#### Decode

소코반 게임의 MapReader클래스에서 초기에 mapdata를 읽어올 때 Decode를 이용해서 암호화되어 있는 map_enc.txt 파일을 복호화하는 역할을 합니다.

java.util.Base64.Decoder를 이용해 복호화 한 데이터를 String으로 반환합니다.

#### MapReader

`readMapFile()` 메서드에서 Decode 클래스를 통해 복호화된 String을 mapdata에 저장할 수 있도록 전처리합니다.

<br>

### 되돌리기 기능 및 되돌리기 취소 기능 구현

u입력 시 초기 위치까지 되돌릴 수 있도록 했습니다.

U입력 시 되돌리기를 취소할 수 있도록 했습니다. 다만 U는 이전의 명령이 u인 경우에만 실행 가능하도록 했습니다.

(ex aaaa(좌4이동) uuuu(제자리로) UUUU(좌4이동))

<br>

#### UserInput 클래스

기존에는 유저의 입력을 받은 후 대문자로 변환하여 return 했었습니다.

하지만 u와 U 를 구분할 필요성이 생겨 대문자로 변환하지 않고 return 하도록 했습니다.

<br>

#### Classification 클래스

사용자의 여러 입력값을 하나씩 실행하며 명령어를 분류한 후 적절한 메서드를 호출하는 클래스입니다.

사용자 입력이 대문자, 소문자 모두 들어오기 때문에 조건문에 대,소문자에 대한 사항을 모두 추가했습니다.

되돌리기 및 되돌리기 취소 시 수행해야 할 메서드와 필요한 변수들을 추가했습니다. `Stack 3개 : rewind, reRewind, pushBall`, `char previousCommad`

u(되돌리기)
- Stack(`rewind`)을 통해 플레이어가 움직일 때마다 push하며 u 입력 시 pop 하여 적절한 되돌리기 메서드(`reverseMoveCommand()`)를 호출합니다.
- 플레이어가 공을 밀면서 움직인 것인지 공을 밀지 않고 움직인 것인지 판별하기 위해 `Stack<Boolean> pushBall`을 선언하여 판별합니다.
- 플레이어가 공을 밀면서 움직였다면 u(되돌리기)에서는 공을 당기면서 움직이게 했습니다.
- reset 시 clear() 합니다.

U(되돌리기 취소)
- Stack(`reRewind`)을 통해 되돌리기 입력을 push하며 U 입력 시 pop 하여 본래의 이동메서드를 호출합니다.
- 이전 명령어가 u가 아니라면 U의 Stack `reRewind`를 clear() 합니다.
- reset 시 clear() 합니다.

사용자 명령 횟수는 `rewind.size()`로 대체했습니다.

기존의 `count`로 명령 횟수를 체크하기 위해서는 count를 이동, 리셋, 되돌리기, 되돌리기 취소 명령마다 ++ 혹은 -- 해줘야 했지만 `rewind.size()`를 통해 명령 횟수를 체크할 수 있어 코드가 간결해 졌습니다.

<br>

#### MovePlayer 클래스

기존에 있었던 `moveWASD(), moveBall(), realMove()` 메서드를 Classification 클래스에서 이동명령 또는 되돌리기 취소명령 시 호출합니다.

`moveWASD()` 메서드에 공을 밀고 움직였는지 또는 플레이어만 움직였는지 결과를 `Stack<Boolean> pushBall`에 저장합니다.

<br>

`reverseMoveWASD(), moveReverseBall(), moveReverse()` 메서드를 새로 생성했습니다. 이 메서드는 되돌리기를 위해 공을 당기도록 설계했습니다.

되돌리기 시 `rewind`에 저장되어있는 명령은 이미 이동한 명령이므로 이동 불가능한 상황 예외사항은 메서드에 추가하지 않았습니다.

`Stack<Boolean> pushBall` 의 결과에 따라 공을 당길 것인지 플레이어만 움직일 것인지 판단하여 적절한 메서드를 호출합니다.

`moveReverseBall()` 공을 당기는 메서드입니다.

`moveReverse()` 플레이어만 이전 위치로 바꾸는 메서드입니다.

<br>

#### Print 클래스

게임시작시 설명에 u, U에 대한 사항을 추가했습니다.

<br>

### 저장하기 불러오기

<br>

**기능구현은 하지 못했지만 생각한 내용을 적겠습니다**

<br>

**구현 완료**

입력 로직 변경 

1S 2S 3S 4S 5S 를 명령어로 입력받아야함

1L 2L 3L 4L 5L 도 마찬가지

루빅스 큐브때처럼 List 이용해서 1~5 입력 뒤에 S or L 이라면 하나로 합친 후에 List에 넣어서 명령어 저장 후에 String[]으로 변환

<br>

___

**미구현**

지도 데이터에서 만약 P (4)가 아닌 구멍 위에 있는 P (5) 라면?

내 로직에서는 불러오기가 에러남.

저장할 때 5는 따로 X로 저장하고 (map data에 X를 추가)

불러 올 때 X 라면 P (5) 로 읽으면 됨.

<br>

저장 할 때 지도 데이터 뿐만 아니라 이동 횟수까지 저장해야 함.

맵데이터 제일 아래에 플레이하던 `StageNum, Stack<Character> rewind, Stack<Character> reRewind, Stack<Boolean> pushBall` 값을 저장해놔야 함.

<br>

1S 2S 3S 4S 5S 별로 map_enc1.txt, map_enc2.txt ..., map_enc5.txt 에 저장

1L 2L 3L 4L 5L 별로 map_enc1.txt, map_enc2.txt ..., map_enc5.txt 에서 읽기
