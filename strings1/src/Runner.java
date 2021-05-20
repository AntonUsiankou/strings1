import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        final String FILE_PATH = "src/in.csv";
        final String PLUS = " + ";
        final String MINUS = " - ";
        final String DELIMITER = ";";
        final String RESULT_LINE_HEAD = "result(";
        final String RESULT_LINE_ENDING = ") = ";
        final String ERROR_LINES = "error-lines =";
        final String ERROR_MESSAGE = "Input file is not found";

        try {
            Scanner sc = new Scanner(new FileReader(FILE_PATH));
            sc.useLocale(Locale.ENGLISH);

            StringBuilder resultLine = new StringBuilder();
            double result = 0.0;
            int errorLines = 0;

            while (sc.hasNext()) {

                String line = sc.next();
                String[] elements = line.split(DELIMITER);

                int index;
                double number;

                try {
                    index = Integer.parseInt(elements[0]);

                    if (index < 0 || index >= elements.length) {
                        errorLines++;
                        continue;
                    }
                    number = Double.parseDouble(elements[index]);

                } catch (Exception e) {
                    errorLines++;
                    continue;
                }

                result += number;
                StringBuilder element = new StringBuilder();

                if (resultLine.length() > 0) {
                    element.append(number < 0 ? MINUS : PLUS).append(Math.abs(number));
                } else {
                    element.append(number);
                }

                resultLine.append(element);
            }

            System.out.println(RESULT_LINE_HEAD + resultLine + RESULT_LINE_ENDING + result);
            System.out.println(ERROR_LINES + errorLines);
        } catch (FileNotFoundException e) {
            System.err.println(ERROR_MESSAGE);
        }
    }
}
