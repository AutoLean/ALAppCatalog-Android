# ALAppCatalog-Android
App Catalog for AutoLean Applications

<img src="http://i.imgur.com/p2fChY4.png" height="300" width="500"/>

# Include in your project
Download the <a href="https://github.com/AutoLean/ALAppCatalog-Android/blob/master/catalog.aar?raw=true">aar</a>

Copy it into your <i>libs</i> folder (Usually <i>your_project/app_module/libs</i>)

Add the following to your build.gradle:

`compile(name:'catalog', ext:'aar')`


`repositories {
  flatDir {
    dirs 'libs'
  }
}`

Add the following to your Application class to initialize Parse<br>
`Parse.enableLocalDatastore(this);`<br>
`ParseObject.registerSubclass(AppCatalogApp.class);`
`ParseObject.registerSubclass(AppCatalogSocialMediaOutlet.class);`

`//Change to your appropriate key for Parse`<br>
`Parse.initialize(this, "APPLICATION_ID","CLIENT_ID");`

# Start
Start the Catalog in your Activity with the following statement:
`Catalog.start(this);`
