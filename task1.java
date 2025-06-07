import java.util.Scanner;

public class task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //  Prompt for two integer numbers
        System.out.print("Enter the first integer: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter the second integer: ");
        int num2 = scanner.nextInt();

        //  Prompt for a floating-point number
        System.out.print("Enter a floating-point number: ");
        double floatNumber = scanner.nextDouble();

        //  Prompt for a single character
        System.out.print("Enter a single character: ");
        char character = scanner.next().charAt(0);

        //  Prompt for a boolean value
        System.out.print("Enter a boolean value (true/false): ");
        boolean boolValue = scanner.nextBoolean();

        scanner.nextLine();

        // Prompt for user's name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        
        int sum = num1 + num2;
        int difference = num1 - num2;
        int product = num1 * num2;

        double floatResult = floatNumber * 2;
        char nextChar = (char)(character + 1);
        boolean opposite = !boolValue;

        
        System.out.println("\nSum of " + num1 + " and " + num2 + " is: " + sum);
        System.out.println("Difference between " + num1 + " and " + num2 + " is: " + difference);
        System.out.println("Product of " + num1 + " and " + num2 + " is: " + product);

        System.out.println(floatNumber + " multiplied by 2 is: " + floatResult);
        System.out.println("The next character after '" + character + "' is: " + nextChar);
        System.out.println("The opposite of " + boolValue + " is: " + opposite);

        System.out.println("Hello, " + name + "!");
    }
}
