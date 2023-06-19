package org.openobservatory.engine;

import oonimkall.CheckInConfig;
import oonimkall.CheckInConfigWebConnectivity;

/** OONICheckInConfig contains configuration for a OONICheckIn. */
public final class OONICheckInConfig {
    /**
     * Charging indicate if the phone is actually charging
     */
    boolean charging;

    /**
     * OnWiFi indicate if the phone is actually connected to a WiFi network
     */
    boolean onWiFi;

    /**
     * Platform of the probe
     */
    String platform;

    /**
     * RunType
     */
    String runType;

    /**
     * softwareName is a mandatory setting specifying the name of
     * the application that is using OONI Probe's engine.
     */
    String softwareName;

    /**
     * softwareVersion is a mandatory setting specifying the version of
     * the application that is using OONI Probe's engine.
     */
    String softwareVersion;

    /**
     * WebConnectivity class contain an array of categories.
     */
    CheckInConfigWebConnectivity webConnectivity;

    public OONICheckInConfig(String softwareName,
                             String softwareVersion,
                             Boolean charging,
                             Boolean onWiFi,
                             String[] categories) {
        this.charging = charging;
        this.onWiFi = onWiFi;
        this.platform = "android";
        this.runType = "timed";
        this.softwareName = softwareName;
        this.softwareVersion = softwareVersion;
        webConnectivity = new CheckInConfigWebConnectivity();
        for (String category : categories) {
            this.webConnectivity.addCategory(category);
        }
    }

    protected CheckInConfig toOonimkallCheckInConfig() {
        CheckInConfig c = new CheckInConfig();
        c.setCharging(charging);
        c.setOnWiFi(onWiFi);
        c.setPlatform(platform);
        c.setRunType(runType);
        c.setSoftwareName(softwareName);
        c.setSoftwareVersion(softwareVersion);
        c.setWebConnectivity(webConnectivity);
        return c;
    }

    public boolean isCharging() {
        return charging;
    }

    public boolean isOnWiFi() {
        return onWiFi;
    }
}
