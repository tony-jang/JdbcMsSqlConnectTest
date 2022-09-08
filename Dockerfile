FROM amazoncorretto:11

WORKDIR /root
COPY . /root

RUN javac /root/src/*.java && cp /root/mssql-jdbc-11.2.0.jre11.jar /root/src/mssql-jdbc-11.2.0.jre11.jar
WORKDIR /root/src
RUN jar cvmf ./META-INF/MANIFEST.MF mssqltest.jar mssql-jdbc-11.2.0.jre11.jar ./*.class
RUN mv mssqltest.jar /root/mssqltest.jar

CMD ["java", "-jar", "/root/mssqltest.jar"]
