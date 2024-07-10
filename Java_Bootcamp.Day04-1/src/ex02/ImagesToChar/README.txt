#1. Compilation
mkdir lib target

#2. Copies jar files into lib directory
cp ./src/resources/*.jar ./lib

#3. Compilation .java files into .class files, put them into target directory
javac -cp ".:./lib/JColor-5.5.1.jar:./lib/jcommander-1.82.jar" -d ./target/ src/java/edu/school21/printer/*/*.java

#4. Unpacks lib jar files to target folder, keeping packages and sub dir
cd target
jar xf ../lib/JColor-5.5.1.jar com
jar xf ../lib/jcommander-1.82.jar com
cd ..

#5. Required by subject
cp -r src/resources target/.

#6. Build jar-file
rm -f ./target/images-to-chars-printer.jar

#7. Packs .class files, resources and a manifest file into a single images-to-chars-printer.jar file
jar -cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

#8. Run program
java -jar target/images-to-chars-printer.jar --white=BRIGHT_RED --black=BRIGHT_GREEN

#9. Remove target directory:
rm -rf target lib