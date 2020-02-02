# See moe.shizuku.preference.PreferenceInflater
-keep public class moe.shizuku.preference.Preference {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keep public class * extends moe.shizuku.preference.Preference {
    public <init>(android.content.Context, android.util.AttributeSet);
}