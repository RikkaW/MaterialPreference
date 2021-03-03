# This project is deprecated

This project was created in the "support library times". In those days, the support preference library even has visual issues, so I choose to create a modified version.

Is very hard to keep up with the official library and the AndroidX Preference library has the most features of this project (customizable divider, SummaryProvider, OnBindEditTextListener, etc), so I decided to deprecate this project.

About SimpleMenuPreference, I will port it to the AndroidX Preference library. It will be a part of RikkaX (https://github.com/RikkaApps/RikkaX).

MaterialPreference
==================

Based on support-preference from Android Support Library, adding some features.

![sample](https://raw.githubusercontent.com/RikkaW/MaterialPreference/master/art/sample.gif)

## Usage

1. Add dependencies

   [![Download](https://api.bintray.com/packages/rikkaw/MaterialPreference/MaterialPreference-Android/images/download.svg)](https://bintray.com/rikkaw/MaterialPreference/MaterialPreference-Android/_latestVersion) (replace `<latest-release>` below with this)

   ```
   maven {
       url "https://dl.bintray.com/rikkaw/MaterialPreference" 
   }
   ```

   First you need to choose whether to use appcompat or not.

   For none appcompat users (like me), use packages with "-android" suffix. Note android variant requires API 21+.

   ```
   implementation 'moe.shizuku.preference:preference-android:<latest-release>'
   ```

   For appcompat users, use packages with "-appcompat" suffix.

   ```
   implementation 'moe.shizuku.preference:preference-appcompat:<latest-release>'
   ```
   
   If you want to use SimpleMenuPreference, add this. Note android variant requires API 23+. On pre-API 21, SimpleMenu is downgrade to normal list.
   ```
   // android
   implementation 'moe.shizuku.preference:preference-simplemenu-android:<latest-release>'
   // appcompat
   implementation 'moe.shizuku.preference:preference-simplemenu-appcompat:<latest-release>'
   ```
2. Add theme
   
   Add `preferenceTheme` to your theme like this.

   ```
   <style name="AppTheme" parent="...">
       ...
       <item name="preferenceTheme">@style/PreferenceThemeOverlay</item>
   </style>
   ```
3. `sample-android` / `sample-appcompat` provides a full example

## Changes for v4.0.0

1. Rearrange packages, split into non-appcompat and appcompat variants. This will reduce the package count and get rid of `accentColorCompat`, `backgroundCompat` or duplicate of resources.
   
   `preference-android` equals previous `preference` + `preference-dialog-android`

   `preference-appcompat` equals previous `preference` + `preference-dialog-appcompat` + `preference-switchcompat`
    
2. preference_category_material.xml paddingTop 16dp -> 24dp
3. preference_recyclerview.xml id @+id/list -> @android:id/list