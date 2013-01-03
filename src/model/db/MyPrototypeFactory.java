/**
 * Represents an implementation of a Prototype factory, using a HashMap.
 * Each prototype is mapped and can be created by calling it's String name representation.
 */
package model.db;

import model.db.errors.ObjectCreationException;

import java.util.HashMap;

public class MyPrototypeFactory extends PrototypeFactory {
	HashMap<String, AbstractPrototype> prototypes;

	/**
	 * Default constructor (eager instantiate the prototype map)
	 */
	public MyPrototypeFactory() {
		prototypes = new HashMap<String, AbstractPrototype>();
	}

	/**
	 * Creates and returns prototype objects by request, via cloning
	 *
	 * @param key the string representation of a prototype object to create (usually ClassName)
	 * @return a clone of the object prototype requested
	 * @throws ObjectCreationException iff the prototype object has failed it's creation process
	 */
	@Override
	public AbstractPrototype createObject(String key) throws ObjectCreationException {
		AbstractPrototype clone = null;
		try {
			clone = (AbstractPrototype) prototypes.get(key).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}

	/**
	 * Adds a new prototype to this factory
	 *
	 * @param prototypeName the String representation of the prototype
	 * @param prototype     the prototype of this object to add
	 */
	@Override
	public void addPrototypeObject(String prototypeName, AbstractPrototype prototype) {
		prototypes.put(prototypeName, prototype);
	}
}
