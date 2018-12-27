# kakaobooks

![build-status](https://travis-ci.com/jeedy/kakaobooks.svg?branch=master)

카카오 검색 API 를 이용해 책 검색 서비스 개발 / 로그인기반 / 책검색/ 북마크 / 검색 히스토리 /  책 상세 페이지 / 

> * 포트는 8000 을 사용합니다. (application.properties 에서 수정 가능.)

> * H2 DB는 memory 방식으로 되어있어 서버를 재시작하면 데이타가 초기화 됩니다. 

> * 가입/로그인 페이지가 통합입니다. 처음 사용자는 자신의 계정과 패스워드를 입력하면 됩니다만 이미 사용중인 계정이라면 패스워드를 정확히 입력해야합니다.


### 서비스 스펙
1. Java 8 버전을 사용
1. framework - spring-boot
1. server - Spring-boot 에서 제공하는 내부서버(Tomcat) 사용 
1. Database - h2 (maven dependency)
1. code style - google code style

### Back-end 추가 모듈(라이브러리)
1. httpclient - for RestAPI connected

### Front-end 추가 모듈(라이브러리)
1. jquery-3.2.1 - Front-end framework.
1. Bootstrap 4-beta - 화면 구성을 위해 CSS만 이용
1. bootpag - jQuery plugin for dynamic pagination


### 설치

```
$ git clone https://github.com/jeedy/kakaobooks.git
$ cd kakaobooks

```

### 실행(로컬)

```
$ export JAVA_HOME="`/usr/libexec/java_home -v '1.8*'`"
$ mvn clean compile
$ mvn spring-boot:run
$ curl -v localhost:8000
```

### 테스트 (Junit)

```
$ export JAVA_HOME="`/usr/libexec/java_home -v '1.8*'`"
$ mvn clean compile test
```


### 배포용 (Jar 압축)

```
$ export JAVA_HOME="`/usr/libexec/java_home -v '1.8*'`"
$ mvn clean compile
$ mvn pakage
$ cd target
```


문의: <kk59491@gmail.com>
