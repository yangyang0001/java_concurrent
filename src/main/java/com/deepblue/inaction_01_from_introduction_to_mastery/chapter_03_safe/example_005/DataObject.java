package com.deepblue.inaction_01_from_introduction_to_mastery.chapter_03_safe.example_005;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataObject {

	private static final ReadWriteLock rwl = new ReentrantReadWriteLock();

	private static final Map<String, Object> dataMap = Maps.newHashMap();

	public Object readWrite(String key) {
		Object result = null;

		try {
			rwl.readLock().lock();
			result = dataMap.get(key);
			if(result == null) {
				rwl.readLock().unlock();

				rwl.writeLock().lock();
				if(result == null) {
					result = "default_value";
				}
				rwl.writeLock().unlock();
			}

			try {
				rwl.readLock().unlock();
			} catch (Exception e) {
			    System.err.println(Thread.currentThread().getName() + " readLock already unlocked ...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
