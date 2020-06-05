// We customize the generated project based on user option

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

// The path where the project got generated
Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

// The properties available to the archetype
Properties properties = request.properties

// Check if customizeCSS option was passed
String customizationAllowed = properties.get("allow-customization")

// If no customization is allowed then we delete css customization files
if (customizationAllowed.equals("none")) {
    Files.deleteIfExists projectPath.resolve("package.json")
    Files.deleteIfExists projectPath.resolve("gulpfile.js")
    
    //Can be optimized -_-
    Files.deleteIfExists projectPath.resolve("src/main/sass/style.scss")
    Files.deleteIfExists projectPath.resolve("src/main/sass/style/_asciidoctor.scss")
    Files.deleteIfExists projectPath.resolve("src/main/sass/style/_layout.scss")
    Files.deleteIfExists projectPath.resolve("src/main/sass/style/_normalize.scss")
    Files.deleteIfExists projectPath.resolve("src/main/sass/style/_responsive.scss")
    Files.deleteIfExists projectPath.resolve("src/main/sass/style")
    Files.deleteIfExists projectPath.resolve("src/main/sass")

    projectPath.resolve("readme-none.adoc").toFile().renameTo "readme.adoc"
} else{
    projectPath.resolve("readme-other.adoc").toFile().renameTo "readme.adoc"
}