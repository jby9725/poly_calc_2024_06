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

        exp = exp.trim(); // 양 옆의 쓸데없는 공백 제거하기

        // 바깥 괄호 제거
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
        // 괄호가 여전히 들어있나요?
        boolean hasBrackets = exp.contains(")") || exp.contains("(");

        String[] bits = null;

        // 여전히 식에 괄호가 있다면
        if(hasBrackets) {
            int bracketsCount = 0;
            // 괄호 수를 세고 여는 괄호에 맞춰 닫는 괄호가 모두 닫혔을 때를 세기 위한 Count.
            int splitPointIndex = -1;
            // 문자열에서 자를 인덱스값 찾기 위해.
            // (10 + 10) + 20 일 때
            // )와 20 사이에 있는 +를 기준으로 잘라야 한다. 이 잘라야 하는 곳을 저장하기 위한 splitPointIndex.

            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '(') {
                    bracketsCount++;
                } else if (exp.charAt(i) == ')') {
                    bracketsCount--;
                }
                // 여는 괄호와 닫는 괄호가 쌍을 다 이뤘을 때.
                if (bracketsCount == 0) {
                    splitPointIndex = i; // 닫는 괄호의 위치
                    break;
                }
            }
            String firstExp = exp.substring(0, splitPointIndex + 1); // '('부터 ')' 까지
            // 괄호가 뒤에 올 경우 firstExp에 들어가는 값이
//            System.out.println("firstExp: " + firstExp);
            String secondExp = exp.substring(splitPointIndex + 4); // 괄호 다음의 연산자 뒤에 있는 숫자부터 끝까지
//            System.out.println("secondExp: " + secondExp);

            String operator = exp.substring(splitPointIndex + 2, splitPointIndex + 3);
//            System.out.println("사이의 연산자 : " + operator);

            if(operator.equals("+")) {
                return Calc.run(firstExp) + Calc.run(secondExp);
            }else if(operator.equals("*")) {
                return Calc.run(firstExp) * Calc.run(secondExp);
            }
        }
        else if (needToCompound) {
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

        // System.out.println("bits: " + Arrays.toString(bits));

        for (int i = 0; i < bits.length; i++) {
            System.out.println("bits[" + i + "] : " + bits[i]);
            numbers.add(Integer.parseInt(bits[i]));
        }

        for (int number : numbers) {
            if (needToPlus) {
                answer = answer + number;
            } else if (needToMultiply) {
                answer = answer * number;
            }
        }

        // System.out.println("answer: " + answer);
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

        if (outerBracketsCount == 0) return exp;

        return exp.substring(outerBracketsCount, exp.length() - outerBracketsCount);
    }
}