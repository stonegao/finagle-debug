resolvers ++= Seq("snapshots" at "http://scala-tools.org/repo-snapshots",
                  "releases"  at "http://scala-tools.org/repo-releases",
                  "Oracle Maven 2 Repository" at "http://download.oracle.com/maven",
                  "JBoss Maven 2 Repository" at "http://repository.jboss.com/maven2",
                  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
                  )

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.0")

addSbtPlugin("com.twitter" % "sbt-package-dist" % "1.1.0-SNAPSHOT")

