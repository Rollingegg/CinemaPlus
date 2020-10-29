#!/usr/bin/env bash
git pull
nohup mvn org.springframework.boot:spring-boot-maven-plugin:2.1.3.RELEASE:run 2> ../out.log