import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class lessonSix {

    private static void createFile(String name, String text, boolean add) {
       try {
            PrintStream ps = new PrintStream(new FileOutputStream(name, add));
            ps.println(text);
            ps.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static void concatFiles(String fileName1, String fileName2, String newFileName) {
        createFile(newFileName, readFile(fileName1),false);
        createFile(newFileName, readFile(fileName2),true);
    }

    private static boolean checkWord (String target, String destination){
        return readFile(destination).contains(target);
    }

    private static String [] checkWordInDirectory(File dir1, String target) {

        File [] filesArr = dir1.listFiles();
        String [] resultArray = new String [0];

        for (int i = 0; i < filesArr.length; i++) {
            if (!filesArr[i].isDirectory()) {
                if (checkWord(target, filesArr[i].toString())) {
                    resultArray = Arrays.copyOf(resultArray, resultArray.length + 1);
                    resultArray[resultArray.length - 1] = filesArr[i].toString();
                }
            }
        }
        return resultArray;
    }

    private static String readFile(String targetFile) {
            try {
                FileInputStream fis = new FileInputStream(targetFile);
                int b;
                String content = "";
               while ((b = fis.read()) != -1) {
                    content += (char) b;
               }
                fis.close();
               return content;
            } catch (IOException e) {
                System.out.println("File not found " + targetFile);
                return "";
            }
        }

    public static void main(String[] args) {
        //Задание 1
        createFile("file1.txt", "Among the facilities provided by the <code>System</code> class\n" +
                " * are standard input, standard output, and error output streams;\n" +
                " * access to externally defined properties and environment\n" +
                " * variables; a means of loading files and libraries; and a utility\n" +
                " * method for quickly copying a portion of an array", false);
        createFile("file2.txt", " * The \"standard\" input stream. This stream is already\n" +
                "     * open and ready to supply input data. Typically this stream\n" +
                "     * corresponds to keyboard input or another input source specified by\n" +
                "     * the host environment or user.", false);

        //Задание 2
        concatFiles("file1.txt", "file2.txt", "file3.txt");

        //Задание 3
        System.out.println("Введите слово для поиска");
        Scanner scanner = new Scanner(System.in);
        System.out.println(checkWord(scanner.next(), "file1.txt"));

        //Задание 4
        File dir = new File(".");
        System.out.println("Введите слово для поиска");
        String[] arrFiles = checkWordInDirectory(dir, scanner.next());

        for (int i = 0; i < arrFiles.length; i++) {
            System.out.println("Слово содержится в файле " + arrFiles[i]);
        }
    }
}
