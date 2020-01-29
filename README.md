CCPA String Builder - utility helper library
=======================================================================

Dependency library for android (or vanilla Java/Kotlin) for creating a California Consumer Privacy Act privacy string.


add the following to your app’s project level build.gradle file inside the repositories section:
```
repositories {
    maven {
        url  "https://fyber.bintray.com/marketplace" 
    }
}
```

Add the following to your app’s app level build.gradle file inside the dependencies section:
```
implementation 'com.fyber.mobile:ccpa-string-builder:1.0'
```

Usage example
-----------------------------------------------------------------------
> Kotlin
```kotlin
val privacyString = CCPAStringBuilder.create()
            .explicitOptOut(CCPAStringComponentValue.YES)
            .optOutSale(CCPAStringComponentValue.YES)
            .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
            .build()
```
> Java
```java
String privacyString = CCPAStringBuilder.create()
                .explicitOptOut(CCPAStringComponentValue.YES)
                .optOutSale(CCPAStringComponentValue.YES)
                .limitedServiceProviderAgreement(CCPAStringComponentValue.YES)
                .build();
```

CCPA String Component Values (value could not be null):
```CCPAComponentStringValue.YES, CCPAComponentStringValue.NO, CCPAComponentStringValue.NOT_APPLICABLE```


Link to California Consumer Privacy Act:
https://iabtechlab.com/wp-content/uploads/2019/11/U.S.-Privacy-String-v1.0-IAB-Tech-Lab.pdf
