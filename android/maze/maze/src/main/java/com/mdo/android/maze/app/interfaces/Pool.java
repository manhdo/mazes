package com.mdo.android.maze.app.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * The Pool object.
 * User: mdo
 * Date: 4/14/14
 * Time: 3:06 PM
 */
public class Pool<T> {
	private final List<T> freeObjects;
	private final PoolObjectFactory<T> factory;
	private final int maxSize;

	public Pool(PoolObjectFactory<T> factory, int maxSize) {
		this.factory = factory;
		this.maxSize = maxSize;
		this.freeObjects = new ArrayList<T>(maxSize);
	}

	public T newObject() {
		T object = null;

		if (freeObjects.size() == 0)
			object = factory.createObject();
		else
			object = freeObjects.remove(freeObjects.size() - 1);

		return object;
	}

	public void free(T object) {
		if (freeObjects.size() < maxSize)
			freeObjects.add(object);
	}

}
