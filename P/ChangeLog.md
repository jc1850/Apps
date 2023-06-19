# OONI Probe Android 2.0.2+43 [2019-02-04]

Fixes:

* IllegalArgumentException in FragmentManagerImpl.moveToState: https://github.com/ooni/probe-android/issues/184
* IllegalStateException in RunningActivity.onFailure: https://github.com/ooni/probe-android/issues/183
* NullPointerException in PerformanceItem.onBindViewHolder: https://github.com/ooni/probe-android/issues/182
* Input dispatching timed out: https://github.com/ooni/probe-android/issues/181
* Hotfix ANR Broadcast of Intent CONNECTIVITY_CHANGE: https://github.com/ooni/probe-android/issues/180
* Memory corruption issues on Android devices: https://github.com/measurement-kit/measurement-kit/issues/1739


# OONI Probe Android 2.0.1+42 [2019-02-01]

Fixes:

* Bug in measurement upload: https://github.com/ooni/probe-android/issues/188

# OONI Probe Android 2.0.0+41 [2019-01-13]

New features:

* This new version includes a major UI overhaul.
* An overview screen for all test results
* Enhanced website testing: you can test a website of your choice, pick categories of sites to test and test country-specific lists
* Data usage tracking on a test by test basis
--
measurement-kit version: android-libs: 0.9.1-android.1

# ooniprobe-android 1.2.3+17 [2017-10-08]

New features:

* Run test screen reloaded when clicking on a second link without dismissing it

* Graphic fixes

measurement-kit version: 0.7.6-1

ooniprobe-wui version: 2.2.4

# ooniprobe-android 1.2.2+16 [2017-09-27]

New features:

* Added support for running tests with custom URI scheme

* Added possibility to open links from run.ooni.io

measurement-kit version: 0.7.4-1

ooniprobe-wui version: 2.2.4

# ooniprobe-android 1.2.2-rc.1+15 [2017-09-27]

* Fixed max_runtime being used when running from URI scheme

* Fixed version name in About screen

measurement-kit version: 0.7.4-1

ooniprobe-wui version: 2.2.4

# ooniprobe-android 1.2.0-rc.5 [2017-09-24] [version yanked]

* Added class to manage version semantic

measurement-kit version: 0.7.3-1

ooniprobe-wui version: 2.2.4

# ooniprobe-android 1.2.0-rc.4 [2017-09-14]

* Fixed a crash on start

# ooniprobe-android 1.2.0-rc.3 [2017-09-11]

* Added possibility to open links from run.ooni.io

# ooniprobe-android 1.2.0-rc.2 [2017-09-09]

* Fixed bug on HTTP Header Field Manipulation result list

# ooniprobe-android 1.2.0-rc.1 [2017-09-07]

New features:

Added support for running tests with custom URI scheme

measurement-kit version: 0.7.1-3

ooniprobe-wui version: 2.2.4-beta.1

# ooniprobe-android 1.1.5 [2017-08-07]

New Languages:

* Added German, Japanese, Chinese (China and Taiwan) translations

New features:

* Bug fixing

* Added dash test

* Added support for push notifications

* Added internal browser to open links from notifications

measurement-kit version: 0.7.0

ooniprobe-wui version: 2.2.4-beta.1

# ooniprobe-android 1.1.4 [2017-05-15]

New Languages:

* Added multilingual support and Persian, Russian, Arabic and Greek translations

Bugfixes:

* Bug fixing

* Added support for right to left layouts

* Added HTTP Header Field Manipulation test            

measurement-kit version: 0.6.5-1

ooniprobe-wui version: 2.2.2

# ooniprobe-android 1.1.3 [2017-03-25]

New Languages:

* Added multilingual support and Spanish, French, Italian and Hindi translations

Bugfixes:

* Various GUI fixing

* Expose app version number in app

* Empty screen in "Past Tests" on init

* Delete all tests button

* Render failed "NDT Speed Test"

measurement-kit version: 0.4.3

ooniprobe-wui version: 2.2.0-rc.6

# ooniprobe-android 1.1.2 [2017-02-13]

Bugfixes:

* JSON file iterator

* Done action in keyboard

* Fixing crash on tap of ooni logo

* Fix crash when running very long input lists

measurement-kit version: 0.4.0-beta.4

ooniprobe-wui version: 2.2.0-rc.0

# ooniprobe-android 1.1.1 [2017-02-09]

Bugfixes:

* Use system resolver when doing DNS queries (this solves an issue with IPv6 lookups)

* Fix problem with tapping of Time row

* Add support for showing test log

* Rename "Max runtime" to "Web Connectivity max runtime"

* Fix issue that leads to scheduled runs not being persistent across reboot

measurement-kit version: 0.4.0-beta.3

ooniprobe-wui version: 2.2.0-rc.0

# ooniprobe-android 1.1.0 [2017-02-08]

This is the first public release of ooniprobe-android.

measurement-kit version: 0.4.0-beta.3

ooniprobe-wui version: 2.2.0-rc.0

# ooniprobe-android 0.1.0-rc.*

This version has never been released and was only present in release tab in github.

The `software_version` appears as either `1.0` (`software_name: ooniprobe-android`) or `software_name: measurement_kit`.

measurement-kit version: various (<0.4.0-beta.1)

ooniprobe-wui version: various (<2.2.0-rc.0)
