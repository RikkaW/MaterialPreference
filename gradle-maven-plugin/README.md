# Gradle Maven Plugin
This is a small "plugin" to simplify the use of *maven-publish* Gradle plugin. 
You can use this plugin for Java Library and Android Library.

# Index

* [Usage](#usage)
* [Customization](#customization)
    * [Generic Project Info](#genericInfo)
    * [Repositories](#repositories)
    * [Example of per-module costumization](#examplePerModule)
* [Library Publish](#publishCommand)
* [Generate POM file](#pomGeneration)

## <a name="usage"/>Usage
In order to use GradleMavenizer you have to add the line

`apply from: 'https://raw.githubusercontent.com/sky-uk/gradle-maven-plugin/master/gradle-mavenizer.gradle'`

at the <b>very bottom</b> of the *build.gradle* file. Alternatively, to use a specific release version, add this property to the project (see Releases at the top of the Github page for released versions):

    mavPluginVersion = '1.0.3'
    
and add this line to the very bottom of the *build.gradle* file:

    apply from: "https://raw.githubusercontent.com/sky-uk/gradle-maven-plugin/${project.mavPluginVersion}/gradle-mavenizer.gradle"`

## <a name="customization"/>Customization
GradleMavenizer is highly customizable.

You can customize it adding some properties to the root project *build.gradle* or to the module *build.gradle* file.

_<b>Attention:</b> If you add the same property to the root project file and to the module file, in order of priority, the module file property will override the root project property._

### <a name="genericInfo"/>1. Generic Project Info (included in POM)

* <b>mavProjectName</b> _-> String property -> Identify the project name (can be different from the artifactId)_
* <b>mavLibraryDescription</b> _-> String property -> A simple description for the library_
* <b>mavDevelopers</b> _-> Map property -> A map with the ids and the developer name of the library_
* <b>mavLibraryLicenses</b> _-> Map property -> A map with the name and the urls of the library licenses_
* <b>mavSiteUrl</b> _-> String property -> Identify the url of the project website_
* <b>mavGitUrl</b> _-> String property ->Identify the url of the project github page_

### <a name="repositories"/>2. Repositories

* Remote repository:
    * <b>mavPublishToRemoteRepo</b> _-> Boolean property -> Allow the script to load your library to a remote repo_
    * <b>mavRemoteRepoUser</b> _-> String property -> Username to grant access to the remote repo_
    * <b>mavRemoteRepoPassword</b> _-> String property -> Password to grant access to the remote repo_
    * <b>mavRepoRemoteUrl</b> _-> String property -> Identify the url of the remote repo_
    
* Internal repository:
    * <b>mavPublishToInternalRepo</b> _-> Boolean property -> Allow the script to load your library to an internal repo_
    * <b>mavRepoInternalUrl</b> _-> String property -> Identify the url of the internal repo_
    
* MavenLocal repository:
    * <b>mavPublishToMavenLocal</b> _-> Boolean property -> Allow the script to load your library to MavenLocal folder_
    
_<b>Attention:</b> If you won't set any repository automatically the script will use MavenLocal as default repo_

### <a name="examplePerModule"/>Example of per-module _build.gradle_ costumization

```
project.ext {
    mavDevelopers = ["Fe":"Federico"]
    mavSiteUrl = "https://github.com/sky-uk/gradle-maven-plugin"
    mavGitUrl = mavSiteUrl + '.git'
    mavProjectName = 'GradleMavenizer'
    mavPublishToInternalRepo = true
    mavRepoInternalUrl = "path/to/internal/repo"
    mavLibraryLicenses = ["Apache-2.0":'http://www.apache.org/licenses/LICENSE-2.0.txt']
    mavLibraryDescription = "A simple description of the project"
}
```

## <a name="publishCommand"/>Library Publish
To finally publish you library you can use the command `./gradlew publish` directly from your project root folder.

You can also pass some properties directly from command line using the command `-P` (every property must have `-P` before), for example:
`./gradlew publish -PmavPublishToMavenLocal=true`

## <a name="pomGeneration"/>Generate POM file
If you want to generate the POM file without publish your library you can use the command `./gradlew generatePomFileForMavenPublishPublication` directly from your project root folder.

To generate the POM file only for one module of your library you can use the command `./gradlew your-module-name:generatePomFileForMavenPublishPublication` where you have to replace `your-module-name` with the name you chose for your module.

_Note: POM file will be saved in `build/publications/mavenPublish/` as `pom-default.xml`_
