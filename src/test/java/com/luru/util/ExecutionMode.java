package com.luru.util;

public enum ExecutionMode {
	HEAD(false), HEADLESS(true);

	private boolean headLessMode;

	private ExecutionMode(boolean headLessMode) {
		this.headLessMode = headLessMode;
	}

	public boolean getMode() {
		return headLessMode;

	}

}
