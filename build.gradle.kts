import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    kotlin("jvm") version "1.4.31"
}

group = "me.imadfndt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = URI("https://www.jitpack.io")
    }
    maven {
        url = URI("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {
    implementation("org.apache.commons:commons-math3:3.0")
    implementation("com.github.AAChartModel:AAChartCore-Kotlin:-SNAPSHOT")
    implementation("no.tornado:tornadofx:1.7.20")

    implementation("com.tambapps.fft4j:fft4j:1.0-SNAPSHOT")

    implementation("com.google.code.gson:gson:2.8.8")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
