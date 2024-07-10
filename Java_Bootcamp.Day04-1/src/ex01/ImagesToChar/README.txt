#1. Create directory
mkdir target

#2. Compilation
javac -d target src/java/edu.school21.printer/app/Program.java src/java/edu.school21.printer/logic/ImageToChar.java

#3. Create resources in target
mkdir -p target/resources

#4. Copy resources from src to resources, change directory to target
cp src/resources/* target/resources/

cd target

#5. Packs .class files, resources and a manifest file into images-to-chars-printer.jar file
jar cvfe images-to-chars-printer.jar edu.school21.printer.app.Program edu/school21/printer/

#6. Run program
java -jar images-to-chars-printer.jar . 0 resources/it.bmp
