package org.koreait;

// 추가 조건
// 연산자 사이는 공백으로 구분한다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calc {

    public static boolean debug = true; // 디버그 하는 중...
    public static int runCallCount = 0; // run 메소드 호출 횟수...

    public static int run(String exp) {
        runCallCount++;
        int answer = 0;

        exp = exp.trim(); // 양 옆의 쓸데없는 공백 제거하기

        // 바깥 괄호 제거
        exp = stripOuterBrackets(exp);


        if (debug) {
            System.out.println("====================================");
            System.out.printf("EXP(%d): `%s`\n", runCallCount, exp);
        }

        if (!exp.contains(" ")) { // 숫자만 들어왔어요.
            return Integer.parseInt(exp);
        }

        // 빼기 연산을 음수를 더하는 것으로 변경
        exp = exp.replaceAll("- ", "+ -");

        boolean needToPlus = exp.contains("+");
        // boolean needToMinus = exp.contains("-"); // 이제 필요가 없어졌다. 빼기를 '음수를 더한다'라는 개념으로 변경했기 때문에.
        boolean needToMultiply = exp.contains("*");

        // 혼합연산이 필요한가?
        boolean needToCompound = needToMultiply && needToPlus;
        // 괄호가 여전히 들어있나요?
        boolean hasBrackets = exp.contains(")") || exp.contains("(");

        String[] bits = null;

        ////////////////
        if (hasBrackets)
            // 괄호 앞에 마이너스가 있다면? 괄호 안을 -1과 곱해야 하니까.
            if(isCaseMinusBracket(exp)){
                exp = exp.substring(1) + " * -1"; // 마이너스 부호 때고 괄호 뒤에 -1 곱하기
            }
//            exp = exp.replaceAll("-\\(", "-1 * (");
        ////////////////

        // 여전히 식에 괄호가 있다면
        if (hasBrackets) {

            // 문자열을 자를 기준으로 삼을 인덱스값
            int splitPointIndex = findSplitPointIndex(exp);

            // 자른 문자열. 이 문자열들은 괄호로 싸여있거나, 싸여있지 않다.
            String firstExp = exp.substring(0, splitPointIndex);
            String secondExp = exp.substring(splitPointIndex + 1);

            if (debug) {
                System.out.println("firstExp: " + firstExp);
                System.out.println("secondExp: " + secondExp);
            }

            // 찾은 구분자를 저장.
            char operator = exp.charAt(splitPointIndex);

            exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);

            return Calc.run(exp);

        } else if (needToCompound) {
            bits = exp.split(" \\+ ");

            // ex) "20 + 10 + 5 * 2 == 40"
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

//        System.out.println("BITS : " + Arrays.toString(bits));

        // 파싱되고 남은 숫자들을 저장할 리스트 numbers
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < bits.length; i++) {
            // System.out.println("bits[" + i + "] : " + bits[i]);
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

    private static boolean isCaseMinusBracket(String exp) {
        // "-(" 로 시작하는지?
        if(exp.startsWith("-(") == false) return false;

        // 괄호로 감싸져 있는지? // 사족...
        int bracketsCount = 0;



        // 사족 끝...

        return true;
    }

    private static int findSplitPointIndex(String exp) {
        // 현재 계산식에서는 덧셈과 곱셈만 처리하면 되기 때문에 구분자로 찾을 문자도 +와 *만 처리하면 되기 때문에 return 하는 경우가 2가지밖에 없다.
//        if(debug) System.out.println("+ 찾아요");
        int index = findSplitPointIndexBy(exp, '+');
        if (index >= 0) return index;

//        if(debug) System.out.println("* 찾아요");
        return findSplitPointIndexBy(exp, '*');
    }

    private static int findSplitPointIndexBy(String exp, char findChar) {
        int bracketsCount = 0; // 괄호 수를 세고 여는 괄호에 맞춰 닫는 괄호가 모두 닫혔을 때를 세기 위한 Count.

        if (debug) {
            System.out.println("findSplitPointIndexBy String exp : " + exp);
            System.out.println("findChar : " + findChar);
        }

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i); // 지금 검사하고 있는 문자 c

            if (c == '(') {
//                if(debug) System.out.println("( 찾았어요");
                bracketsCount++;
            } else if (c == ')') {
//                if(debug) System.out.println(") 찾았어요");
                bracketsCount--;
            } else if (c == findChar) {
                // 여는 괄호와 닫는 괄호가 쌍을 다 이루지 않았을 때
                if (bracketsCount != 0) // i가 괄호 안 문자열 안에 있는 곱셈 또는 덧셈을 가리키게 된다. 이 연산자를 기준으로 나누면 안된다.
                    if(debug) System.out.println("괄호 안에 "+findChar+" 연산자가 있어요");
                // 여는 괄호와 닫는 괄호가 쌍을 다 이뤘을 때
                if (bracketsCount == 0) {
                    if (debug) System.out.println("괄호 바깥에 "+findChar+" 연산자가 있어요");
                    return i; // 괄호의 바깥에 있는 연산자. 이 연산자를 기준으로 나누기 위해 return을 해준다.
                }
            }
        }
        return -1;
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