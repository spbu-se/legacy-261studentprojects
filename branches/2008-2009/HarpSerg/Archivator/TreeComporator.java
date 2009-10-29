package archiver;

/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
import java.util.Comparator;
public class TreeComporator implements Comparator<Tree> {

    @Override
    public int compare(Tree t1, Tree t2) {
        if (t1.weight > t2.weight) {
            return 1;
        }
        if (t1.weight < t2.weight) {
            return -1;
        }
        if (t1.weight == t2.weight) {
            return 0;
        }
        return 0;
    }

    public boolean nodeExists(Tree t) {
        if (t != null && t.weight != 0) {
            return true;
        } else {
            return false;
        }
    }
}
