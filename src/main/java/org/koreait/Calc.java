package org.koreait;

// 추가 조건
// 연산자 사이는 공백으로 구분한다.

import java.util.ArrayList;
import java.util.List;

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

        // 파싱되고 남은 숫자들을 저장할 리스트 numbers
        List<Integer> numbers = new ArrayList<>();

        for (String bit : bits) {
            numbers.add(Integer.parseInt(bit));
        }
        answer = numbers.get(0);
        // 아래 for문을 돌 때, 가장 처음의 값의 부호가 마이너스인 경우로 무조건 상정하게 되기 때문에 이를 방지하기 위해 0번째 값을 미리 가지고 있는다.

        for (int i = 1; i < numbers.size(); i++) {
            if (needToPlus) {
                answer = answer + numbers.get(i);
            }
            else if (needToMinus) {
                answer = answer - numbers.get(i);
            }
        }

        return answer;
//        throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");

    }
}
