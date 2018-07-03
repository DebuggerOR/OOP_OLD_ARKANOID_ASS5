# 318732484
# foglero
compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar:. -d bin @sources.txt
run:
	java -cp biuoop-1.4.jar:bin Ass5Game 1 2 3 4
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java    
bin:
	mkdir bin
jar:
	mkdir uber-jar
	unzip biuoop-1.4.jar -d uber-jar
	rm -rf uber-jar/META-INF
	cp -r bin/* uber-jar/
	jar -cfm ass5game.jar Manifest.mf -C uber-jar/ .
	rm -rf uber-jar
