package encryptdecrypt;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //без паттернов и разделения на классы

        String operation = new String();
        String message = new String("");
        String inFile = new String();
        String outFile = new String();
        String algo = new String();
        int key = 0;


        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-mode")) {
                if ((i + 1 != args.length) && !(args[i].equals("-key")) && !(args[i].equals("-data")) &&
                        !(args[i].equals("-in")) && !(args[i].equals("-out")) && !(args[i].equals("-alg"))) {
                    operation = args[i + 1];
                } else {
                    operation = "enc";
                }
            }

            if (args[i].equals("-key")) {
                if ((i + 1 != args.length) && !(args[i].equals("-mode")) && !(args[i].equals("-data")) &&
                        !(args[i].equals("-in")) && !(args[i].equals("-out")) && !(args[i].equals("-alg"))) {
                    key = Integer.parseInt(args[i + 1]);
                } else {
                    key = 0;
                }
            }

            if (args[i].equals("-data")) {
                if ((i + 1 != args.length) && !(args[i].equals("-key")) && !(args[i].equals("-mode")) &&
                        !(args[i].equals("-in")) && !(args[i].equals("-out")) && !(args[i].equals("-alg"))) {
                    message = new String(args[i + 1]);
                } else {
                    message = "";
                }
            }

            if (args[i].equals("-in")) {
                if ((i + 1 != args.length) && !(args[i].equals("-key")) && !(args[i].equals("-mode")) &&
                        !(args[i].equals("-data")) && !(args[i].equals("-out")) && !(args[i].equals("-alg"))) {
                    inFile = new String(args[i + 1]);
                } else {
                    inFile = "";
                }
            }

            if (args[i].equals("-out")) {
                if ((i + 1 != args.length) && !(args[i].equals("-key")) && !(args[i].equals("-mode")) &&
                        !(args[i].equals("-data")) && !(args[i].equals("-in")) && !(args[i].equals("-alg"))) {
                    outFile = new String(args[i + 1]);
                } else {
                    outFile = "";
                }
            }

            if (args[i].equals("-alg")) {
                if ((i + 1 != args.length) && !(args[i].equals("-key")) && !(args[i].equals("-mode")) &&
                        !(args[i].equals("-data")) && !(args[i].equals("-in")) && !(args[i].equals("-out"))) {
                    algo = new String(args[i + 1]);
                } else {
                    algo = "";
                }
            }

        }

        Main m = new Main();

        if (!inFile.equals("")) {
            try {
                message = new String(Files.readAllBytes(Paths.get(inFile)));
            } catch (IOException e) {
            }

        }

        if (outFile.equals("")) {
            switch (operation) {
                case "enc":
                    if (algo.equals("unicode")) {
                        System.out.println(m.encriptionUnicode(key, message));
                    } else if (algo.equals("shift")) {
                        System.out.println(m.encriptionAlf(key, message));
                    }
                    break;
                case "dec":
                    if (algo.equals("unicode")) {
                        System.out.println(m.decriptionUnicode(key, message));
                    } else if (algo.equals("shift")) {
                        System.out.println(m.decriptionAlf(key, message));
                    }
                    break;
            }
        } else {
            File file = new File(outFile);
            try {
                FileWriter fw = new FileWriter(file, false);
                switch (operation) {
                    case "enc":
                        if (algo.equals("unicode")) {
                            fw.write(m.encriptionUnicode(key, message));
                        } else if (algo.equals("shift")) {
                            fw.write(m.encriptionAlf(key, message));
                        }
                        fw.close();
                        break;
                    case "dec":
                        if (algo.equals("unicode")) {
                            fw.write(m.decriptionUnicode(key, message));
                        } else if (algo.equals("shift")) {
                            fw.write(m.decriptionAlf(key, message));
                        }
                        fw.close();
                        break;
                }
            } catch (IOException e) {
                switch (operation) {
                    case "enc":
                        if (algo.equals("unicode")) {
                            System.out.println(m.encriptionUnicode(key, message));
                        } else if (algo.equals("shift")) {
                            System.out.println(m.encriptionAlf(key, message));
                        }
                        break;
                    case "dec":
                        if (algo.equals("unicode")) {
                            System.out.println(m.decriptionUnicode(key, message));
                        } else if (algo.equals("shift")) {
                            System.out.println(m.decriptionAlf(key, message));
                        }
                        break;
                }
            }
        }
    }

    public String encriptionUnicode(int key, String message) {
        StringBuilder answ = new StringBuilder("");

        for (int i = 0; i < message.length(); i++) {
            char a = message.charAt(i);
            char b = (char) ((int) a + key);
            answ.append(b);
        }
        return answ.toString();
    }

    public String decriptionUnicode(int key, String message) {
        StringBuilder answ = new StringBuilder("");

        for (int i = 0; i < message.length(); i++) {
            char a = message.charAt(i);
            char b = (char) ((int) a - key);
            answ.append(b);
        }

        return answ.toString();
    }

    public String encriptionAlf(int key, String message) {
        StringBuilder answ = new StringBuilder("");

        for (int i = 0; i < message.length(); i++) {
            char a = message.charAt(i);
            char b = ' ';

            if (a >= 65 && a <= 90) {
                b = (char) ((int) a + key);
                if (!(b >= 65 && b <= 90)) {
                    b = (char) ((int) b - 26);
                }
            } else if (a >= 97 && a <= 122) {
                b = (char) ((int) a + key);
                if (!(b >= 97 && b <= 122)) {
                    b = (char) ((int) b - 26);
                }
            } else {
                b = a;
            }
            answ.append(b);
        }
        return answ.toString();
    }

    public String decriptionAlf(int key, String message) {
        StringBuilder answ = new StringBuilder("");

        for (int i = 0; i < message.length(); i++) {
            char a = message.charAt(i);
            char b = ' ';

            if (a >= 65 && a <= 90) {
                b = (char) ((int) a - key);
                if (!(b >= 65 && b <= 90)) {
                    b = (char) ((int) b + 26);
                }
            } else if (a >= 97 && a <= 122) {
                b = (char) ((int) a - key);
                if (!(b >= 97 && b <= 122)) {
                    b = (char) ((int) b + 26);
                }
            } else {
                b = a;
            }
            answ.append(b);
        }
        return answ.toString();
    }

}

