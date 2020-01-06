plugins {
    kotlin("jvm") version "1.3.61"
    id("com.jfrog.bintray") version "1.7"
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
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(tasks.jar.get().outputs.files.first()) {
                classifier = "jar"
            }
            artifact(tasks.kotlinSourcesJar.get().outputs.files.first()) {
                classifier = "sources"
            }
        }
    }
}

bintray {
    user = if (project.hasProperty("bintrayUser")) project.property("bintrayUser") as String else System.getenv("BINTRAY_USER")
    key = if (project.hasProperty("bintrayApiKey")) project.property("bintrayApiKey") as String else System.getenv("BINTRAY_API_KEY")
    setPublications("maven")
    pkg.apply {
        repo = "marketplace"
        name = project.name
        userOrg = user
        setLicenses("MIT")
        vcsUrl = "https://github.com/Heyzap/ccpa-string-builder-kotlin-java"
        version.apply {
            name = project.version as String
        }
    }
}

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