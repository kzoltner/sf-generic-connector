rootProject.name = "GenericConnector"

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    mavenLocal()
  }
}

include("extensions:common:vault:vault-filesystem-pem")