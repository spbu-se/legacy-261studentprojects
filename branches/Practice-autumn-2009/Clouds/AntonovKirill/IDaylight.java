/*
 * Interface for Daylight class
 * have list of daylight types
 * Antonov Kirill 2009
 */

package clouds;


public interface IDaylight {

	public enum DaylightType {Morning, Noon, Evening, Night}

	public DaylightType getDaylight();

}