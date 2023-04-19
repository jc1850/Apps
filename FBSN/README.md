[![status-badge](https://ci.codeberg.org/api/badges/pstorch/F-Droid_Build_Status/status.svg)](https://ci.codeberg.org/pstorch/F-Droid_Build_Status)
<a href="https://translate.codeberg.org/engage/f-droid_build_status/">
<img src="https://translate.codeberg.org/widgets/f-droid_build_status/-/strings-xml/svg-badge.svg" alt="Translation state">
</a>

# <img src="app/src/main/ic_launcher-playstore.png" alt="F-Droid Build Status" height="40"></img> F-Droid Build Status

Android App to show and monitor the current F-Droid build status. It uses the F-Droid build server
API.

<a href="https://f-droid.org/en/packages/de.storchp.fdroidbuildstatus/">
                <img alt="Get it on F-Droid" src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png" height="60" align="middle">
</a>

Get nightly builds
from <a href="https://fdroid.storchp.de/fdroid/repo?fingerprint=99985A7E73DCB0B16C9BDDCE7A0B4996F88068AE7C771ED53E217E69CD1FF196">https://fdroid.storchp.de/fdroid/repo</a>

## Features

- Shows successful, failed and missing builds
- Shows if an app needs an update, is disabled, archived, has no apk package or has disabled update
  check
- Shows published versions and highest metadata version of an app
- Shows build log
- Mark apps as favourite
- Notify about new build status, optionally only of your favourite apps

## F-Droid API

The App uses the following F-Droid API endpoints:

- **running.json**: the current running F-Droid buildserver
  process, https://f-droid.org/repo/status/running.json
- **build.json**: the last completed F-Droid buildserver build
  cycle, https://f-droid.org/repo/status/build.json
- **update.json**: the last update check of the F-Droid
  buildserver, https://f-droid.org/repo/status/update.json
- **build.log**: `https://f-droid.org/repo/{id}_{versionCode}.log.gz`
- **published package info**: `https://f-droid.org/api/v1/packages/{id}`,
  example https://f-droid.org/api/v1/packages/org.fdroid.fdroid

## Screenshots

<div>
    <img width="25%" src="fastlane/metadata/android/en-US/images/phoneScreenshots/1-mainscreen.jpg">
    <img width="25%" src="fastlane/metadata/android/en-US/images/phoneScreenshots/2-builddetails.jpg">
    <img width="25%" src="fastlane/metadata/android/en-US/images/phoneScreenshots/3-settings.jpg">
    <img width="25%" src="fastlane/metadata/android/en-US/images/phoneScreenshots/4-about.jpg">
    <img width="25%" src="fastlane/metadata/android/en-US/images/phoneScreenshots/5-legend.jpg">
    <img width="25%" src="fastlane/metadata/android/en-US/images/phoneScreenshots/6-buildlog.jpg">
</div>

## Legend

![currently running build cycle](artwork/directions_run-24px.svg) currently running build cycle (
running.json)

![last finished build cycle](artwork/build_24.svg) finished build cycle (build.json)

![successful build](artwork/check-24px.svg) successful build

![failed build](artwork/error_outline-24px.svg) failed build, incomplete build cycle (
maxBuildTimeReached)

![missing build](artwork/help_outline-24px.svg) missing build

![non-favourite app](artwork/star_border-24px.svg) non-favourite app

![favourite app](artwork/star-24px.svg) favourite app

![disabled app](artwork/disabled.svg) disabled app

![archived app](artwork/archive_24dp.svg) archived app

![no packages app](artwork/no_packages.svg) app has no packages

![no update check](artwork/no_update_check.svg) app has no update check enabled

![app needs update](artwork/upgrade_24dp.svg) app needs update / update cycle

## Translations

This app is translated at: <a href="https://translate.codeberg.org/projects/f-droid_build_status/">
translate.codeberg.org</a>.

## Useful information

- [General F-Droid documentation page](https://f-droid.org/en/docs/)
- [F-Droid update processing](https://f-droid.org/en/docs/Update_Processing/)
- [How exactly does the building process work?](https://gitlab.com/fdroid/wiki/-/wikis/FAQ#how-exactly-does-the-building-process-work)
- [How long does it take for my app to show up on website and client?](https://gitlab.com/fdroid/wiki/-/wikis/FAQ#how-long-does-it-take-for-my-app-to-show-up-on-website-and-client)

## Artwork

- Logo derived from F-Droid: https://gitlab.com/fdroid/artwork
- Icons from: https://github.com/material-components/material-components
- Acryl painting from @mondstern: https://pixelfed.social/p/mondstern/254646998685323264 (many
  thanks  :heart_eyes:)

## Chat

If you would like to get in touch:

Matrix: `#fdroid-build-status:matrix.org` or
via [Element.io](https://app.element.io/#/room/#fdroid-build-status:matrix.org)

## Related Projects

- F-Droid Monitor: https://monitor.f-droid.org/builds
- fdroid-build-checker: https://github.com/johnjohndoe/fdroid-build-checker

