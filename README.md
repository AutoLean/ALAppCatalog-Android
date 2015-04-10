# ALAppCatalog-Android
App Catalog for AutoLean Android Apps

# Include in your project
Download the <a href="https://github.com/AutoLean/ALAppCatalog-Android/blob/master/catalog.aar?raw=true">aar</a>

Copy it into your <i>libs</i> folder (Usually Your_Project/app_module/libs)

Add the following to your build.gradle:

`compile(name:'catalog', ext:'aar')`


`repositories {
  flatDir {
    dirs 'libs'
  }
}`

# Start
Start the Catalog in your Activity with the following statement:
`Catalog.start(this);`
