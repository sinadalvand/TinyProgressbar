# Tiny Progressbar
[![Developer](https://img.shields.io/badge/developer-sina%20dalvand-orange)](https://github.com/sinadalvand)
[![Lisense](https://img.shields.io/badge/License-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/sinadalvand/maven/TinyProgressbar/images/download.svg?version=1.0.1) ](https://bintray.com/sinadalvand/maven/TinyProgressbar/1.0.1/link)

Tiny animated progressbar whit reverse animation


<img src="https://github.com/sinadalvand/HandleProgressbar/blob/master/art/preview.gif" width="250"/>

<img src="https://github.com/sinadalvand/HandleProgressbar/blob/master/art/demo.gif" width="250"/>

## Gradle & Maven
```
 implementation 'ir.sinadalvand.libs:tinyprogressbar:{Last_release_version}'
```
```
<dependency>
	<groupId>ir.sinadalvand.libs</groupId>
	<artifactId>tinyprogressbar</artifactId>
	<version>1.0.1</version>
	<type>pom</type>
</dependency>
```


## How use this Progressbar :
```	
	<ir.sinadalvand.libs.tinyprogressbar.TinyProgressbar
        app:bgColor="@color/colorAccent"                  ==> progress Background Color
        app:progressColor="@color/colorPrimary"           ==> progress Color
        app:animationDuration="1000"                      ==> animation Duration in milisec (1000 = 1s)
        app:autoStart="true"                              ==> auto start in screen load !
        app:progressRelative="0.3"                        ==> Size of Progressbar compared to whole view width (viewWidth * 0.3)
        app:roundCorner="true"                            ==> if true make round progressbar and background corner
        app:progressHeight="3dp"                          ==> progressbar height 
        android:id="@+id/tinyProgressbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
       />

```


### For start progressing :
```
TinyProgressbar.start()
```

### For transform to Handle :
```
TinyProgressbar.stop()
```



Programically settings:

```
TinyProgressbar.setProgressbarBackground(Int color)
TinyProgressbar.setProgressbarColor(Int color)
TinyProgressbar.setProgressWidth(Int px)
TinyProgressbar.setProgressRelative(Float per) 
TinyProgressbar.setProgressHeight(Int px) 
TinyProgressbar.setAnimationDuration(Long millisecond) 
```


#Who use this Lib:
```
Rose Movie Apllication
<img src="https://github.com/sinadalvand/HandleProgressbar/blob/master/art/real.gif" width="250"/>
```



# License

    Copyright 2019 Sina Dalvand

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.















