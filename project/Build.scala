import sbt._
import Keys._
import PlayProject._

/*
 * Temporarily using source code with specified commit ID because released salat plugin has open-ended dependencies.
 */
// TODO: Remove 'Version' and 'SalatPlugin' and '.dependsOn...' when released salat plugin has fixed dependencies.
object Version {
  val commitId = "8378c69aa4710cb8007c14d955d6b6be327eea0c"
}

object SalatPlugin {
  lazy val head = RootProject(uri("git://github.com/leon/play-salat.git#%s".format(Version.commitId)))
}

object ApplicationBuild extends Build {

    val appName         = "versioned-patient-data"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "se.radley" %% "play-plugins-salat" % "1.0.3"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      routesImport += "se.radley.plugin.salat.Binders._",
      templatesImport += "org.bson.types.ObjectId"
    ).dependsOn(SalatPlugin.head)

}
