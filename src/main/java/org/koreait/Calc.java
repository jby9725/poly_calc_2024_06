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

        if(!exp.contains(" ")){ // 숫자만 들어왔어요.
            return Integer.parseInt(exp);
        }

        // 빼기 연산을 음수를 더하는 것으로 변경
        exp = exp.replaceAll("- ", "+ -");

        boolean needToPlus = exp.contains("+");
        // boolean needToMinus = exp.contains("-"); // 이제 필요가 없어졌다.
        boolean needToMultiply = exp.contains("*");

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
}
//////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////

//      이하 내가 야매로 해본것 (곱셈 하나 이후 덧셈 여러개) - 재귀 X

////////////////////////////////////////////////////////////////
// {
//        int answer = 0;
//
//        // 빼기 연산을 음수를 더하는 것으로 변경
//        exp = exp.replaceAll("- ", "+ -");
//
//        boolean needToPlus = exp.contains("+");
//        // boolean needToMinus = exp.contains("-"); // 이제 필요가 없어졌다.
//        boolean needToMultiply = exp.contains("*");
//
//        boolean needToCompound = needToMultiply && needToPlus;
//
//        String[] bits = null;
//
//        bits = exp.split(" ");
//        List<Integer> indexOfPlus = new ArrayList<>();
//        List<Integer> indexOfMultiply = new ArrayList<>();
//
//        for (int i = 0; i < bits.length; i++) {
//            if (bits[i].equals("+")) {
//                indexOfPlus.add(i);
//            } else if (bits[i].equals("*")) {
//                indexOfMultiply.add(i);
//            }
//        }
//
//        if (indexOfMultiply.isEmpty()) { // 더하기만 있을 때 (빼기도 포함된다.)
//            System.out.println("더하기만 있어요");
//            // 파싱되고 남은 숫자들을 저장할 리스트 numbers
//            List<Integer> numbers = new ArrayList<>();
//
//            bits = exp.split(" \\+ ");
//
//            for (String bit : bits)
//                numbers.add(Integer.parseInt(bit));
//
//            for (int number : numbers)
//                answer = answer + number;
//
//            System.out.println("only plus answer: " + answer);
//            return answer;
//
//        } else { // 곱하기 있을 때. 더하기가 있을 수 있음
//            if (needToPlus) { // 곱하기와 더하기 함께 있을 때
//                System.out.println("곱하기 더하기 있어요");
//
//                // 곱하기
//                answer = Integer.parseInt(bits[indexOfMultiply.get(0) - 1]) * Integer.parseInt(bits[indexOfMultiply.get(0) + 1]);
//
//                // 더하기
//                for (int i = 0; i < indexOfPlus.size(); i++)
//                    answer += Integer.parseInt(bits[indexOfPlus.get(i) - 1]);
//
////                for (int i = 0; i < bits.length; i++) {
////                    if (bits[i].equals("*")) {
////                        System.out.println("곱하고있어요");
////                        int tmp = Integer.parseInt(bits[i - 1]) * Integer.parseInt(bits[i + 1]);
////                        answer = answer + tmp;
////                        needToMultiply = false;
////                    } else if (!needToMultiply && bits[i].equals("+")) {
////                        System.out.println("더하고있어요");
////                        answer += Integer.parseInt(bits[i + 1]);
////                    }
////                }
//                System.out.println("multi and plus answer: " + answer);
//                return answer;
//
//            } else { // 곱하기만 있을 때
//                System.out.println("곱하기만 있어요");
//                // 파싱되고 남은 숫자들을 저장할 리스트 numbers
//                answer = 1;
//                List<Integer> numbers = new ArrayList<>();
//
//                bits = exp.split(" \\* ");
//
//                for (String bit : bits)
//                    numbers.add(Integer.parseInt(bit));
//
//                for (int number : numbers)
//                    answer = answer * number;
//                System.out.println("only multi answer: " + answer);
//                return answer;
//            }
//        }
//    }
//}