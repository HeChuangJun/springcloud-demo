package com.ajun.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * 计算工具类
 */
public class CalculateUtil {

    /**
     * 计算表达式的值
     * @param expr 23*(32-22)+19.6 + 1 - a*b
     * @param map a:1,b:2
     * @return
     */
    public static BigDecimal calculate(String expr, Map<String,Object> map) {
        return EvulateExpression(FormatExpreesion(expr,map));
    }

    private static BigDecimal EvulateExpression(String expression) {
        Stack<Character> opertorStack = new Stack<>();    //操作符栈 用来存放操作符
        Stack<BigDecimal> numberStack = new Stack<>();       //数字栈 存放数字

        String[] tokens = expression.split(" ");              //用" "对格式化之后的表达式进行切割
        for (String token : tokens) {                               //遍历token
            if (token.length() == 0) {
                continue;
            } else if (token.charAt(0) == '+' || token.charAt(0) == '-') {    //当遇到加减时
                while (!opertorStack.isEmpty() && (opertorStack.peek() == '+' || opertorStack.peek() == '-'
                        || opertorStack.peek() == '*'
                        || opertorStack.peek() == '/')) {
                    processAnOperator(opertorStack, numberStack);
                }
                opertorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {     //当遇到乘除
                while (!opertorStack.isEmpty() && (opertorStack.peek() == '*' || opertorStack.peek() == '/')) {
                    processAnOperator(opertorStack, numberStack);
                }
                opertorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '(') {                               //左括号
                opertorStack.push(token.charAt(0));
            } else if (token.charAt(0) == ')') {                               //右括号
                while (opertorStack.peek() != '(') {
                    processAnOperator(opertorStack, numberStack);
                }
                opertorStack.pop();
            } else {
                numberStack.push(new BigDecimal(token));
            }
        } while(!opertorStack.isEmpty()){                                     //确保数字栈空
            processAnOperator(opertorStack, numberStack);
        }
        return numberStack.pop();
    }

    private static void processAnOperator(Stack<Character> opertorStack, Stack<BigDecimal> numberStack) {
        //这个方法用来计算
        BigDecimal num1 = numberStack.pop();
        BigDecimal num2 = numberStack.pop();
        char c = opertorStack.pop();
        switch (c) {
            case '+':
                numberStack.push(num2.add(num1));
                break;
            case '-':
                numberStack.push(num2.subtract(num1));
                break;
            case '*':
                numberStack.push(num2.multiply(num1));
                break;
            case '/':
                numberStack.push(num2.divide(num1));
                break;

        }
    }

    private static String FormatExpreesion(String expression,Map<String,Object> map) {

        //用来格式化表达式
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
                sb.append(' ');
                sb.append(c);
                sb.append(' ');
            } else {
                sb.append(c);
            }
        }
        String result = sb.toString();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            result = result.replaceAll(key, (String) map.get(key));
        }
        return result;
    }
}

