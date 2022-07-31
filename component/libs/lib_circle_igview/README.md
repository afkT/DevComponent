
# About

clone CircleImageView 修改源码使用 ( 用于演示, 推荐使用 Material ShapeableImageView )

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="de.hdodenhof.circleimageview" />
```

# main/java 目录结构

```
- java                                
   - de                               
      - hdodenhof                     
         - circleimageview            
```

# Usage

```xml
<de.hdodenhof.circleimageview.CircleImageView
	xmlns:app="http://schemas.android.com/apk/res-auto"
	android:id="@+id/vid_profile_civ"
	android:layout_width="96dp"
	android:layout_height="96dp"
	android:src="@drawable/profile"
	app:civ_border_width="2dp"
	app:civ_border_color="#FF000000"/>
```