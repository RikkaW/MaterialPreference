MaterialPreference
==================

Based on support-preference from Android Support Library, adding some features.

![sample](https://raw.githubusercontent.com/RikkaW/MaterialPreference/master/art/sample.gif)

## Usage

1. Add dependencies

   ![image](https://api.bintray.com/packages/rikkaw/MaterialPreference/preference-android/images/download.svg) (replace `<latest-release>` below with this)

   Before new packages is added to JCenter, you need to add this.
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