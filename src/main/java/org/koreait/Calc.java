package org.koreait;

// 추가 조건
// 연산자 사이는 공백으로 구분한다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calc {
    public static int run(String exp) {
        int answer = 0;

        // 괄호 제거
        exp = stripOuterBrackets(exp);

        System.out.println("EXP: " + exp);

        if (!exp.contains(" ")) { // 숫자만 들어왔어요.
            return Integer.parseInt(exp);
        }

        // 빼기 연산을 음수를 더하는 것으로 변경
        exp = exp.replaceAll("- ", "+ -");

        boolean needToPlus = exp.contains("+");
        // boolean needToMinus = exp.contains("-");
        // 이제 필요가 없어졌다. 빼기를 '음수를 더한다'라는 개념으로 변경했기 때문에.
        boolean needToMultiply = exp.contains("*");
        // 혼합연산이 필요한가?
        boolean needToCompound = needToMultiply && needToPlus;

        String[] bits = null;

        if (needToCompound) {
            bits = exp.split(" \\+ ");

            // new... 잘 알아봅시다. // "20 + 10 + 5 * 2 == 40"
            String newExp = Arrays.stream(bits) // bits : 20 / 10 / 5 * 2
                    .mapToInt(Calc::run)// 1: 20 return / 2: 10 return / 3: 5*2 계산 후 10 return
                    // 1 : Calc.run("20") 2: Calc.run("10") 3:Calc.run("5 * 2")
                    .mapToObj(e -> e + "") // 형변환. int to string "20" "10" "10"
                    .collect(Collectors.joining(" + ")); // "20 + 10 + 10"

            return run(newExp);
        }
        if (needToPlus) {
            bits = exp.split(" \\+ "); // 더하기는 역슬래시 2개 넣은 다음 써야한다. 모르면? 검색 : java split plus
        } else if (needToMultiply) {
            answer = 1;
            bits = exp.split(" \\* ");
        }


        // 파싱되고 남은 숫자들을 저장할 리스트 numbers
        List<Integer> numbers = new ArrayList<>();

        for (String bit : bits) {
//            System.out.println(bit);
            numbers.add(Integer.parseInt(bit));
        }

        for (int number : numbers) {
            if (needToPlus) {
                answer = answer + number;
            } else if (needToMultiply) {
                answer = answer * number;
            }
        }

//        System.out.println("answer: " + answer);
        return answer;
        // throw new RuntimeException("해석 불가 : 올바른 계산식이 아닙니다.");

    }

    // 바깥 괄호 삭제 메소드
    public static String stripOuterBrackets(String exp) {
        // 바깥쪽의 괄호가 몇 겹인지.
        int outerBracketsCount = 0;

        while (exp.charAt(outerBracketsCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketsCount) == ')') {
            outerBracketsCount++;
        }

        if(outerBracketsCount == 0) return exp;

        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);
    }
}