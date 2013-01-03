/**
 * Represents a Prototype factory that holds prototypes and enables creating them
 * This works like a 'window-shopper': you can see what objects available, and choose to create from it
 */
package model.db;

import model.db.errors.ObjectCreationException;

public abstract class PrototypeFactory {
	/**
	 * Creates and returns prototype objects by request
	 *
	 * @param objectName the string representation of a prototype object to create (usually ClassName)
	 * @return the object prototype requested
	 * @throws ObjectCreationException iff the prototype object has failed it's creation process
	 */
	public abstract AbstractPrototype createObject(String objectName) throws ObjectCreationException;

	/**
	 * Adds a new prototype to this factory
	 *
	 * @param prototypeName the String representation of the object
	 * @param prototype     the prototype of this object to add
	 */
	public abstract void addPrototypeObject(String prototypeName, AbstractPrototype prototype);
}
