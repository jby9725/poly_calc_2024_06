package org.koreait;

// 추가 조건
// 연산자 사이는 공백으로 구분한다.

public class Calc {
    public static int run(String exp) {
        int answer = 0;

        boolean needToPlus = exp.contains("+");
        boolean needToMinus = exp.contains("-");

        String[] bits = null;

        if (needToPlus) {
            bits = exp.split(" \\+ "); // 더하기는 역슬래시 2개 넣은 다음 써야한다. 모르면? 검색 : java split plus
            // `\\+` 만 하면 ` 1`과 `1 `이 나온다. 이를 파싱하려고 하면 오류가 난다.
            // 그래서 구분자의 양옆에 공백을 넣으면 잘 파싱이 된다.
        } else if (needToMinus) {
            bits = exp.split(" \\- ");
        }

        int a = Integer.parseInt(bits[0]);
        int b = Integer.parseInt(bits[1]);

        if (needToPlus) {
            answer = a + b;
            return answer;

        } else if (needToMinus) {
            answer = a - b;
            return answer;
        }

        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");
    }
}
