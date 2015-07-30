# ALAppCatalog-Android
App Catalog for AutoLean Applications

<img src="http://i.imgur.com/p2fChY4.png" height="300" width="500"/>

# Include in your project
`repositories {
	    maven { url "https://jitpack.io" }
	}`
	
`dependencies {
      compile 'com.github.AutoLean:ALAppCatalog-Android:v1.2'
}`

Extend the `CatalogApp.class` with your Application class and implement the required methods.

# Start
Start the Catalog in your Activity with the following statement:
`Catalog.start(this);`


License
--------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
