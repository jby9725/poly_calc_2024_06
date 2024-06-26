# 최종 목표
- 예 : 3 * 1 + ( 1 - ( 4 * 1 - (1 - 1 ))) >>> 답이 0 나오도록

# 과정
1. 더하기만 가능하도록 만들었다.
    - 인자로 받은 문자열을 자르기  위해 split()을 사용하였다.
    - split() 내에서 '+'를 구분자로 하기 위해서는 역슬래시 2개 넣은 다음 써야한다.
      - 자세한 것은 검색 java split plus
2. 공백 포함되어 파싱하여 오류가 발생하는 문제 해결
3. 빼기도 가능하도록 만들었다.
    - contains() 함수를 사용하였다.
      - 어떠한 문자열에 매개변수로 전달하는 문자열이 있는지 없는지 true / false로 리턴한다.
    - if - else if 로 "+" 와 "-" 의 경우 두 가지의 경우에 대응할 수 있도록 만들었다.
4. 더하거나 빼는 숫자들이 늘어났다.
   - 예 : 10 + 20 + 30 == 60
   - 예 : 60 - 20 - 10 == 30
   - 숫자들을 담은 변수 a,b를 리스트로 변환하여 처리하였다. (commit 10 + 20 + 30)
5. 연산할 때 더하기와 빼기를 함께 한다.
   - 더이상 빼기만 더하기만 하는것이 아니라 빼기도 더하기도 함께 있다.
   - 빼기를 음수를 더하는 것으로 바꿀 수 있다!! (생각 못했다..)
   - 이러면 더하기와 빼기 둘 중 더하기만 처리하면 된다.
6. 곱하기만 할 때도 가능하도록 만들었다.
   - 더하기나 빼기는 초기값 0에서 시작해도 문제가 없었지만 곱셈은 0에서 곱하면 무조건 0이 되기 때문에 바람직하지 않다.
   - 따라서 " * "가 문자열에 있을 때 초기값을 1로 세팅하여 곱셈만 실행하는 데에는 문제가 없도록 하였다.
7. 더하기, 곱하기 함께 있을 때
   - 더하기만 있을 때, 곱하기만 있을 때, 함께 있을 때 연산을 다르게 처리했다.
   - 더하기만 있을 때, 곱하기만 있을 때는 이전의 코드를 참고하였다.
   - 더하기, 곱하기 함께 있고, 단 곱하기가 가장 마지막 계산 항일 때의 경우에 대응하도록 코드를 짰다.
   - 이전에 빼기 연산의 경우 음수를 더하는 방식으로 변경했기 때문에 더하기, 빼기, 곱하기 연산이 혼합(단, 곱하기 연산이 가장 뒤)되어 있는 경우도 대응할 수 있다. 
8. 재귀함수 사용
   - 이전의 코드에서 착안해서 "10 + 5 * 2"를 처리할 때
     - 10 + (5 * 2)
     - 5 * 2 먼저 계산 ==> 10
     - 10 + 10
     - 20
   - 위와 같은 과정을 거쳐 계산을 한다고 했을 때, 기존에 만들어져 있는 단순 더하기, 단순 곱셈으로 순차적으로 계산이 가능하다.
   - 괄호 안을 계산한다 >> 함수를 호출한다 의 개념으로 생각하였다.
9. 재귀함수를 사용하여 계산식에서 숫자의 갯수가 3개일 때(ex: 10 + 5 * 2)에는 잘 작동이 되었지만, 그 이상의 식(ex: 20 + 10 + 5 * 2)에서는 NullPointerException 이 발생했다. 
   - 왜? : 디버깅을 해보면, 곱셈식을 처리하기 위해 재귀함수를 빠져나왔을 때 남아있는 식이 숫자만 남아 이 이후로 계산이 안된다.  
   - 그렇다면? : 처음에 exp가 숫자만 들어왔을 때 그대로 그 수를 return 해주면 된다.
   - 그 다음에는? : 리턴받은 숫자를 모아 계산하면 된다.
10. 스트림과 재귀함수를 사용하여 곱셈과 더하기가 순서에 상관없이 처리될 수 있도록 하였다.
    - split() : 먼저 더하기로 처음 받은 문자열을 나누고, 나누어진 문자열들을 살펴보면 덧셈으로 이루어져있던 항들은 숫자만 남고, 곱셈은 그대로 남아 계산을 기다린다.
    - .mapToInt(Calc::run) : 이 상황에, 모든 항들을 재귀함수로 실행하여 숫자만 있을땐 숫자만 리턴, 계산식이 남아있을 땐(단순식 하나만 남아있는 상태) 계산하여 계산한 값을 리턴한다.
    - .mapToObj(e -> e + "") : 이 작업이 끝나면 숫자형으로 계산이 중간까지 끝난 상태의 스트림이 남아있고, 이를 문자열들로 바꾸고
    - .collect(Collectors.joining(" + ")) : 서로의 결과에 덧셈을 한 문자열을 마지막으로 뱉어낸다.
    - run(newExp) : 마지막 문자열은 덧셈으로만 이루어진 단순 계산식이기 때문에 run()함수를 한번 실행하면 된다.
11. 가장 바깥의 괄호 연산 추가
    - 괄호가 있으면 파라미터에 문제가 생겼다.
    - 일단 괄호를 제거하면 단순 계산에는 문제가 생기지 않겠지?
    - stripOuterBrackets() 메소드를 만들고 한 겹의 괄호를 없앴다.
    - stripOuterBrackets() 메소드를 변경
      - 바깥 괄호의 개수를 세는 변수를 만들고
      - 그에 따라서 괄호를 제외한 알맹이를 잘라 반환
12. 일반 괄호 연산 추가
    - ex) (20 + 20) + 20 == 60
    - Calc.run("(20 + 20)") + Calc.run("20") 의 형태가 되면 된다.
    - 그러기 위해서는 )와 2 사이에 있는 +를 기준으로 문자열을 나누어 계산할 필요가 있다.
      1. 전체 문자열에서 바깥 괄호를 없애고도 괄호가 있는지 판별해야한다. hasBrackets
      2. 괄호가 있다면? 
         - 여는 괄호의 수와 닫는 괄호의 수가 같아질 때 (괄호가 완벽하게 닫혔을 때) 그 이후에 있는 연산자를 기준으로 나누자.
      3. 이렇게 찾은 연산자의 위치를 이용해서 괄호 안의 식과 그 이후의 식을 계산해주자.
         - 이건 기존에 추가된 "바깥 괄호 연산" 과 "숫자만 남은 식"을 가지고 연산하기 때문에 코드에 큰 문제가 생기지 않는다.

