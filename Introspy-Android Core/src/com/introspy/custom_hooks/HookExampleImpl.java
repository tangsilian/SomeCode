package com.introspy.custom_hooks;

import com.introspy.core.IntroHook;

public class HookExampleImpl extends IntroHook {
	@Override
	public void execute(Object... args) {
		// _logBasicInfo();
		// _logParameter();
		_logFlush_I("Method:");
		_logFlush_I("woshileigangwu");
	}
}
