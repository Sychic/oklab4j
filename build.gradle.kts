plugins {
    id("java")
    `maven-publish`
}

group = "dev.sychic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.bundles.ejml)
}

tasks.test {
    useJUnitPlatform()
}

java.toolchain {
    languageVersion = JavaLanguageVersion.of(8)
}

publishing {

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://repo.sychic.dev")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}