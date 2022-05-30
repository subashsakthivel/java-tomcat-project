package module.reader.wc;

import java.io.IOException;

public class Wc extends Utils {

    private static String version(){
        return ("Version 1.01");
    }

    private String help() {
                   return ("""
                            wc file (or) files -> bytes chars words lines
                            wc -l file -> no of lines
                            wc -w file -> no of words
                            wc -m file -> no of characters
                            wc -c file -> no of bytes
                            wc -L file -> no of Longest line
                            Try this""");
    }

    private String readWithCmd(char cmd , String path) throws IOException {
            return switch (cmd) {
                case 'l' -> path + " " +getLines(path);
                case 'w' -> path + " " +getWords(path);
                case 'm' -> path + " " +getChars(path);
                case 'c' -> path + " " +getBytes(path);
                case 'L' -> path + " " +getLongLines(path);
                case 'i' -> path + " " + getInfo(path , true);
                default -> help();
            };
    }

    public Wc()  {

    }

    public String printResult(String[] args) throws IOException {
        int length = args.length;
        switch (length) {
            case 0:
                return help();// nothing is passed
            case 1:
                if (args[0].equals("--help")) return help();
                else if (args[0].equals("--version")) return version();
                else return readWithCmd('i', args[0]);
            case 2:
                if (args[0].charAt(0) == '-')
                    return readWithCmd(args[0].charAt(1), args[1]);
            default:
                return getInfo(args);
        }
    }
}

