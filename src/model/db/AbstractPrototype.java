/**
 * Represent an abstractPrototype object that can be cloned to get instances of it.
 */
package model.db;

public abstract class AbstractPrototype implements Cloneable {
	/**
	 * Clones this prototype object
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
