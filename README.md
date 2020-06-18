# PermissionChecker
This is library for permission checker. 


 Import Permission Checker dependency
------------------------------------

declare it into your pom.xml

```xml
<dependency>
  <groupId>com.github.josmilan.permissionchecker</groupId>
  <artifactId>checker</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
or into your build.gradle

```groovy
dependencies {
    implementation 'com.github.josmilan.permissionchecker:checker:1.0.0'
}
```

 How to use
------------
declare our permission checker class

```xml
private var permissionChecker: RuntimePermissionChecker? = null
```

declare permission to be checked and need to show the popup

```xml
private val permissionToCheck = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
```

Dont forget to add it in the manifest also

Intialize our permission checker

```xml
if (supportFragmentManager.findFragmentByTag(RuntimePermissionChecker.TAG) != null)
            permissionChecker =
                supportFragmentManager.findFragmentByTag(RuntimePermissionChecker.TAG) as RuntimePermissionChecker

        if (permissionChecker == null) {
            permissionChecker = RuntimePermissionChecker.newInstance()
            supportFragmentManager.beginTransaction()
                .add(permissionChecker!!, RuntimePermissionChecker.TAG)
                .commit()
        }
```

Whenever we need to check and show the popup for enabling permission

```xml
val permissionGranted: Boolean =
                permissionChecker!!.checkPermissions(permissionToCheck)
            if (permissionGranted) {
                \\code to be done if permission is already granted
            }
```

Now its time for the callback from the permission granted or denied.
1. Implement interface "RuntimePermissionChecker.PermissionCallback"
2. Override its methods
    1. onPermissionGranted - Do the code which we need to do on permission granted
    2. onPermissionDenied - do the action if permission denied
    
    
Thanks Everyone
