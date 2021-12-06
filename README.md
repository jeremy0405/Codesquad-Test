# 구현과정 상세 설명

## 1단계

<br>

**Revision 번호** : 18

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

데이터의 종류에 따라 map을 세팅합니다. #, O, o, P 에 따라 알맞은 int 값을 배정해주고 Player의 위치는 Position 객체를 통해 저장합니다.

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

## 3단계

## 4단계
