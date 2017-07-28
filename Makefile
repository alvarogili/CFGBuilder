construct:
	mvn clean install
	mkdir target/sources
	cp files/sources/* target/sources

.PHONY: construct

