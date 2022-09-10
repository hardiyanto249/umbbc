package com.real.umbbc.controller;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayObjMsisdn implements Delayed {
	private String data;
	private long startTime;

	public DelayObjMsisdn(String data, long delay) {
		this.data = data;
		this.startTime = System.currentTimeMillis() + delay;
	}

	public long getDelay(TimeUnit unit) {
		long diff = startTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	public int compareTo(Delayed o) {
		if (this.startTime < ((DelayObjMsisdn) o).startTime) {
			return -1;
		}
		if (this.startTime > ((DelayObjMsisdn) o).startTime) {
			return 1;
		}
		return 0;
	}

	public String toString() {
		return data;
		// return "{" + "data='" + data + '\'' + ", startTime=" + startTime +
		// '}';
	}
}
