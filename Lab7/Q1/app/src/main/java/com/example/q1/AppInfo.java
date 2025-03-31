package com.example.q1;



public class AppInfo {
    private String name;
    private String packageName;

    public AppInfo(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }
}
