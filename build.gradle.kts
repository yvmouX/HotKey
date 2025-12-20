plugins {
    java
    `maven-publish`
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("com.gradleup.shadow") version "9.3.0"
}

group = "com.yvmoux"
version = "1.0.0"

repositories {
    mavenCentral()
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
}

dependencies {
    implementation(files(rootProject.file("lib/YLib-1.0.0-beta5-all.jar")))
    compileOnly("org.spigotmc:spigot-api:1.21.8-R0.1-SNAPSHOT")
}

val targetJavaVersion = 21
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion

    // 若当前 JDK 版本低于目标版本，使用 Toolchain 指定 JDK 版本
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

// Java 编译任务配置
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    // 针对 Java 10+ 启用 --release 参数
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

tasks {
    runServer {
        minecraftVersion("1.21")
        jvmArgs("-Dfile.encoding=UTF-8", "-Dsun.stdout.encoding=UTF-8", "-Dsun.stderr.encoding=UTF-8")
    }
}

tasks.shadowJar {
    relocate("cn.yvmou.ylib", "com.playerPlugin.playerTaskX.lib.ylib")
}


// 资源处理任务配置（替换 plugin.yml 中的版本变量）
tasks.processResources {
    val props = mapOf("version" to project.version.toString())
    inputs.properties(props)
    filteringCharset = "UTF-8"

    // 对 plugin.yml 进行变量替换（例如 ${version} 替换为项目版本）
    filesMatching("plugin.yml") {
        expand(props)
    }
}