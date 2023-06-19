package org.openobservatory.ooniprobe.test.suite;

import androidx.annotation.Nullable;

import com.google.common.collect.Lists;

import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.common.PreferenceManager;
import org.openobservatory.ooniprobe.test.test.AbstractTest;
import org.openobservatory.ooniprobe.test.test.FacebookMessenger;
import org.openobservatory.ooniprobe.test.test.Signal;
import org.openobservatory.ooniprobe.test.test.Telegram;
import org.openobservatory.ooniprobe.test.test.Whatsapp;

import java.util.ArrayList;

public class InstantMessagingSuite extends AbstractSuite {
	public static final String NAME = "instant_messaging";

	public InstantMessagingSuite() {
		super(NAME,
				R.string.Test_InstantMessaging_Fullname,
				R.string.Dashboard_InstantMessaging_Card_Description,
				R.drawable.test_instant_messaging,
				R.drawable.test_instant_messaging_24,
				R.color.color_cyan6,
				R.style.Theme_MaterialComponents_Light_DarkActionBar_App_NoActionBar_InstantMessaging,
				R.style.Theme_MaterialComponents_NoActionBar_App_InstantMessaging,
				R.string.Dashboard_InstantMessaging_Overview_Paragraph,
				"anim/instant_messaging.json",
				R.string.small_datausage);
	}

	public static InstantMessagingSuite initForAutoRun() {
		InstantMessagingSuite suite = new InstantMessagingSuite();
		suite.setAutoRun(true);
		return suite;
	}

	@Override public AbstractTest[] getTestList(@Nullable PreferenceManager pm) {
		if (super.getTestList(pm) == null) {
			ArrayList<AbstractTest> list = new ArrayList<>();
			if (pm == null || pm.isTestWhatsapp())
				list.add(new Whatsapp());
			if (pm == null || pm.isTestTelegram())
				list.add(new Telegram());
			if (pm == null || pm.isTestFacebookMessenger())
				list.add(new FacebookMessenger());
			if (pm == null || pm.isTestSignal())
				list.add(new Signal());
			super.setTestList(Lists.transform(list, test->{
				if (getAutoRun()) test.setOrigin(AbstractTest.AUTORUN);
				return test;
			}).toArray(new AbstractTest[0]));
		}
		return super.getTestList(pm);
	}
}
