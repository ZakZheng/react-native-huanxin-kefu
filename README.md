
# react-native-huanxin-kefu

## Getting started

`$ npm install react-native-huanxin-kefu --save`

### Mostly automatic installation

`$ react-native link react-native-huanxin-kefu`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.zakzheng.huanxin.kefu.RNHuanxinKefuPackage;` to the imports at the top of the file
  - Add `new RNHuanxinKefuPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-huanxin-kefu'
  	project(':react-native-huanxin-kefu').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-huanxin-kefu/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-huanxin-kefu')
  	```


## Usage
```javascript
import RNHuanxinKefu from 'react-native-huanxin-kefu';

// TODO: What to do with the module?
RNHuanxinKefu;
```
  