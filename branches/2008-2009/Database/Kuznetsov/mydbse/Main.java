package mydbse;

/**
 *
 * @author Kirill Kuznetsov
 */
import java.io.*;
import java.util.Comparator;

public class Main {

    /**
     * @param args the command line arguments
     */
    private static final int INIT_VALUE = -1;
    public static final int ALL = 0;
    private static final int ERROR_CODE = -1;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Searcher yandex;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String from, to, request;
        int number, keyType[], prevKeyType[] = new int[2];
        String[] arguments;
        Comparator c;


        for (int i = 1; i < prevKeyType.length; ++i) {
            prevKeyType[i] = INIT_VALUE;
        }
        if (args.length != 1) {
            printHelp();
        } else {
            yandex = new Searcher(args[0]);
            fullReading(args[0]);
            request = getRequest();
            while (!request.equals("q")) {
                arguments = request.split(" ");
                if (arguments.length < 3 || arguments.length > 4) {
                    printRequestHelp();
                } else {
                    from = arguments[1];
                    to = arguments[2];
                    if (arguments.length == 3) {
                        number = ALL;
                    } else {
                        number = Integer.parseInt(arguments[3]);
                    }
                    keyType = parseKey(arguments[0]);
                    if (keyType[0] == ERROR_CODE) {
                        printHelp();
                    } else if (keyType != prevKeyType) {
                        prevKeyType = keyType;
                        if (keyType[1] == INIT_VALUE) {
                            c = new OneFieldComp(keyType[0]);
                        } else {
                            c = new TwoFieldComp(keyType[0], keyType[1]);
                        }
                        yandex.makeIndex(c);
                    }
                    yandex.search(from, to, number);
                }
                request = getRequest();
            }
        }

    }

    private static int[] parseKey(String commonKey) {
        int[] fields = new int[2];
        String[] keys = commonKey.split(":");
        if (keys.length < 0 || keys.length > 2) {
            printHelp();
            fields[0] = ERROR_CODE;
        } else if (keys.length == 1) {
            if (keys[0].equals("tel")) {
                fields[0] = Record.TEL;
                fields[1] = INIT_VALUE;
            } else if(keys[0].equals("sn")) {
                fields[0] = Record.SURNAME;
                fields[1] = INIT_VALUE;
            } else {
                printHelp();
                fields[0] = ERROR_CODE;
            }
        } else  if (keys[0].equals("tel")) {
            fields[0] = Record.TEL;
            fields[1] = Record.SURNAME;
        } else if (keys[0].equals("sn")){
            fields[0] = Record.SURNAME;
            fields[1] = Record.TEL;
        } else {
            printHelp();
            fields[0] = ERROR_CODE;
        }
        return fields;
    }

    private static void fullReading(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader_test = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        long begin = System.nanoTime();
        while (reader_test.ready()) {
            reader_test.readLine();
        }
        System.out.println("Full reading time:\n" + (System.nanoTime() - begin) + " ns");
    }

    private static void printRequestHelp() {
        System.out.println("How to use: <key_type>[:<second_key_type>] " +
                "<from_key>[:<from_second_key>] <to_key>[:<to_second_key>] [<how_many_records_to_show>]");
        System.out.println("* key_type:\n sn - to search by surname\n tel - to search by telephone number\n " +
                "sn:tel or tel:sn to combine");
        System.out.println("* you may not to state how many recrods you need in case you need all of them");
        System.out.println("* type \"q\" if you want to quit");
    }

    private static String getRequest() throws IOException {
        System.out.println("Input your request");
        return reader.readLine();
    }

    private static void printHelp() {
        System.out.println("MyDataBaseSearchEngine Copyright (c) 2009 Kirill Kuznetsov");
        System.out.println("How to use: java -jar <mydbse.jar_path> <database_file>");
    }
}