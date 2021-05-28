import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        final String FILE_PATH = "src/in2.csv";
        final String ERROR_MESSAGE = "Input file is not found";
        final String BLANK_SIGN = " ";
        final String PLUS = BLANK_SIGN + "+" + BLANK_SIGN;
        final String MINUS = BLANK_SIGN + "-" + BLANK_SIGN;
        final String DELIMETER = ";";
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String ERROR_LINES = "error-lines = ";

        final int SIGN_LENGTH = MINUS.length();
        final char CHAR_MINUS = '-';
        final int BEGINING_SIGN = MINUS.indexOf(CHAR_MINUS);

        try {
            Scanner scanner = new Scanner(new FileReader(FILE_PATH));
            scanner.useLocale(Locale.ENGLISH);

            StringBuilder stringResult = new StringBuilder();
            double numResult = 0.0;
            int errorLines = 0;

            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(DELIMETER);
                try {
                    int index = Integer.parseInt(words[0]);

                    double number = Double.parseDouble(words[index]);

                    numResult += number;

                    stringResult.append(number < 0 ? MINUS : PLUS).append(Math.abs(number));
                } catch (Exception e) {
                    errorLines++;
                }
            }
            if (stringResult.length() > 0) {
                char symbol = stringResult.charAt(BEGINING_SIGN);
                stringResult.delete(0 , SIGN_LENGTH);
                if(symbol == CHAR_MINUS) {
                    stringResult.insert(0, CHAR_MINUS);
                }
            }
            System.out.println(RESULT_HEAD + stringResult + RESULT_TAIL + numResult);
            System.out.println(ERROR_LINES + errorLines);

        } catch (FileNotFoundException e) {
            System.err.println(ERROR_MESSAGE);
        }
    }
}