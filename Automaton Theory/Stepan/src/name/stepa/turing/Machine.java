package name.stepa.turing;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Machine {

    public boolean isStopped;
    public int currentState;
    public MachineRule[] rules;
    public InfiniteTape tape;
    public boolean verbose;


    public Machine(int startState, String startTape, MachineRule[] rules) {
        this.currentState = startState;
        this.rules = rules;
        this.tape = new InfiniteTape(startTape);
        this.isStopped = false;
        this.verbose = false;
    }

    public boolean iterate() {
        if (isStopped)
            return false;

        char currentValue = tape.readValue();
        for (MachineRule rule : rules) {
            if ((rule.oldState == currentState) && ((rule.oldValue == currentValue) ||
                    (rule.oldValue == MachineRule.ANY_VALUE))) {

                if (verbose) {
                    System.out.println(String.format("Selected rule: (%d,%c)->(%d,%c,%s)", rule.oldState, rule.oldValue, rule.newState, rule.newValue, rule.movement.toString()));
                }

                if (rule.newValue != MachineRule.ANY_VALUE) {
                    tape.writeValue(rule.newValue);
                }

                if (rule.movement == Move.HALT) {
                    isStopped = true;
                    currentState = rule.newState;
                    return false;
                }

                currentState = rule.newState;

                if (rule.movement == Move.LEFT)
                    tape.moveLeft();
                else if (rule.movement == Move.RIGHT)
                    tape.moveRight();

                return true;
            }
        }

        isStopped = true;
        return false;
    }

    @Override
    public String toString() {
        return "(" + currentState + "," + tape + ")";
    }
}
