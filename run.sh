#!/usr/bin/env bash
#!/bin/bash
PROCESS_NAME=CinemaPlus
kill -9 $(ps -eaf|grep -i "java .*-classpath .*${PROCESS_NAME}"|grep -v 'grep'|awk '{print $2}')
git pull
nohup mvn org.springframework.boot:spring-boot-maven-plugin:2.1.3.RELEASE:run 2> ../out.log