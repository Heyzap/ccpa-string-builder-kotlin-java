import org.jetbrains.kotlin.utils.addToStdlib.safeAs

plugins {
    kotlin("jvm") version "1.3.61"
    id("com.jfrog.bintray") version "1.8.4"
    maven
    `maven-publish`
}

group = "com.fyber.mobile"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.0")
}

publishing {
    publications {
        register("maven", MavenPublication::class) {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(tasks.jar.get().outputs.files.first()) {

            }
            artifact(tasks.kotlinSourcesJar.get().outputs.files.first()) {
                classifier = "sources"
            }
            pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")
                configurations.implementation.allDependencies.forEach {
                    // Exclude filetree dependencies.
                    if (it.name != "unspecified") {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.apply {
                            appendNode("groupId", it.group)
                            appendNode("artifactId", it.name)
                            appendNode("version", it.version)
                        }
                        it.safeAs<ModuleDependency>()?.let { moduleDep ->
                            if (moduleDep.artifacts.count() > 0) {
                                dependencyNode.appendNode("type", moduleDep.artifacts.toList()[0].type)
                            }
                        }
                    }
                }
            }
        }
    }
}

bintray {
    user = if (project.hasProperty("bintrayUser")) project.property("bintrayUser") as String else System.getenv("BINTRAY_USER")
    key = if (project.hasProperty("bintrayApiKey")) project.property("bintrayApiKey") as String else System.getenv("BINTRAY_API_KEY")
    setPublications("maven")
    publish = true
    pkg.apply {
        repo = "marketplace"
        name = project.name
        userOrg = "fyber"
        setLicenses("MIT")
        vcsUrl = "https://github.com/Heyzap/ccpa-string-builder-kotlin-java"
        version.apply {
            name = project.version as String
        }
    }
}

tasks.findByName("generatePomFileForMavenPublication")?.dependsOn(tasks.assemble)

tasks {
    assemble {
        dependsOn("kotlinSourcesJar")
    }
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    bintrayUpload {
        dependsOn(project.tasks.build)
    }
}