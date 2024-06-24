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
4. ...