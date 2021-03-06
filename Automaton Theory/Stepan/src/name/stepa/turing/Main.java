package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Main {

    public static void main(String[] args) {

        boolean verbose = false;
        int parIndex = 0;
        if (args[0].equals("-t")) {
            parIndex++;
            verbose = true;
        }


        Machine m = MachineFactory.createMachine(args[parIndex], args[parIndex + 1]);
        m.verbose = verbose;

        if (verbose)
            System.out.println(m);
        while (m.iterate())
            if (verbose)
                System.out.println(m);
        System.out.println(m);
    }
}
