package org.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Basic calculator program that can handle arithmetic operations with MDAS operation hierarchy.
public class Main {
    //Setup
    private static Calculator calculator = new Calculator(); //For using arithmetic operations
    private static final Scanner scanner = new Scanner(System.in); //For getting user input in console

    static List<Float> numbers = new ArrayList();
    static List<String> operators = new ArrayList();
    static Boolean invalidInput = true;
    static Boolean invalidNumberOfOperators = false;
    static String errorMsg = "";

    public static void main(String[] args) {

        do{
            System.out.println("Enter expression");
            System.out.println("Example: 2+4/10*2-20");
            String input = scanner.nextLine();

            //Replace all whitespace lines with empty char
            // \\s is the symbol for whitespace lines
            input = input.replaceAll("\\s","");

            System.out.println(input);

            // \\d is the symbol for digits, + means all the occurrences
            String[] operatorsStringArray = input.split("\\d+");
            String[] operands = input.split("[+\\-*/]"); // \\- is needed to make the character a string literal

//            System.out.println(operatorsStringArray.length);
//            System.out.println(operands.length);
//
//            System.out.println(Arrays.toString(operatorsStringArray));
//            System.out.println(Arrays.toString(operands));

            if(operands[0].isEmpty()){
                invalidNumberOfOperators = true;
                errorMsg = "Missing operand";
            }

            for (String operand : operands) {
                if (!operand.isEmpty()) { // Handle empty tokens (e.g., when input starts with an operator)
                    try {
                        Float num = Float.parseFloat(operand);
                        numbers.add(num);
                        invalidInput = false;
                    } catch (Exception e) {
                        invalidInput = true;
                        errorMsg = "Cannot parse string into number (float)";
                    }

                }
            }

            for (String operator : operatorsStringArray){
                if(!operator.isEmpty()){
                    //Since input is already cleaned of whitespace,
                    //operators with empty operands will automatically not be checked
                    //e.g 1++1. 2-+/3
                    if(!operator.equals("+")&&!operator.equals("-")&&!operator.equals("/")&&!operator.equals("*")){
                        invalidInput = true;
                        errorMsg = "Operator is invalid.";
                    }else{
                        operators.add(operator);
                        invalidInput = false;
                    }
                }
            }

            if(invalidInput || invalidNumberOfOperators){
                System.out.println("Invalid input, please try again");
                System.out.println(errorMsg);
                System.out.println();
            }
        }while(invalidInput || invalidNumberOfOperators);

//        System.out.println("List of numbers: " + "\t"+ numbers);
//        System.out.println("List of operators: " + "\t"+ operators);

        //Start Calculation
        float result = 0;
        //MDAS
        int x = 0;
        while(operators.contains("*") || operators.contains("/")){
            //Multiplication and Division
            for(int count = 0; count < operators.size(); count++){
                if(operators.get(count).equals("*")){
                    result = calculator.Multiply(numbers.get(count), numbers.get(count+1));
                    operators.remove(count);
                    numbers.remove(count);
                    numbers.remove(count);

                    numbers.add(count, result);

                    break;
                }
                if(operators.get(count).equals("/")){
                    result = calculator.Divide(numbers.get(count), numbers.get(count+1));
                    operators.remove(count);
                    numbers.remove(count);
                    numbers.remove(count);

                    numbers.add(count, result);

                    break;
                }
            }
            x++;

        }
        //System.out.println("Loop has reached: " + x + " repetitions");

        while(operators.contains("+") || operators.contains("-")){
            //Division
            for(int count = 0; count < operators.size(); count++){
                if(operators.get(count).equals("+")){
                    result = calculator.Add(numbers.get(count), numbers.get(count+1));
                    operators.remove(count);
                    numbers.remove(count);
                    numbers.remove(count);

                    numbers.add(count, result);

                    break;
                }

                if(operators.get(count).equals("-")){
                    result = calculator.Subtract(numbers.get(count), numbers.get(count+1));
                    operators.remove(count);
                    numbers.remove(count);
                    numbers.remove(count);

                    numbers.add(count, result);

                    break;
                }
            }
            x++;
        }
        //System.out.println("Loop has reached: " + x + " repetitions");

        //System.out.println("List of numbers: " + "\t"+ numbers);
        //System.out.println("List of operators: " + "\t"+ operators);

        System.out.println("Result is " + result);
    }
}