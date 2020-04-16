import java.nio.file.Files

def url = args[0] // website to scrape
def baseUrl = args[1] // base url where to resolve images upon
def saveFolder = args[2] // output directory for the images

def imagePattern = ~/.*src="((.*).(jpg|png))".*/
def imagePredicate = imagePattern.asPredicate()

def outputFile = new File(saveFolder)

new URL(url).readLines()
        .stream()
        .filter { imagePredicate.test(it) }
        .map { it.takeAfter("src=\"").takeBefore("\"") }
        .forEach { image ->
            def imageUrl = "$baseUrl/$image"
            def output = new File(outputFile, image).toPath()
            println imageUrl
            if (!Files.exists(output)) {
                Files.copy(new URL(imageUrl).openStream(), output)
            }
        }
