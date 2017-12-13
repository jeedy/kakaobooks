# kakaobooks
카카오 검색 API 를 이용해 책 검색 서비스 개발 / 로그인기반 / 책검색/ 북마크 / 검색 히스토리 /  책 상세 페이지 / 

> 제출용 프로젝트 특성상 설치가 없이 소스 받아 실행할 수 있도록 내장 어플리케이션을 사용했습니다. 

### 기술 track

1. Java 8 버전을 사용
1. framework - spring-boot
1. server - Spring-boot 에서 제공하는 내부서버(Tomcat) 사용 
1. Database - h2 (maven dependency)
1. code style - google code style

### 설치

<https://github.com/jeedy/kakaobooks.git>

1. <https://github.com/jeedy/kakaobooks.git> 에서 `Fork` 버튼 클릭하고,
2. 포크 저장소 계정 선택
3. git fetch or pull or clone
4. 포크 설정 [Configuring a remote for a fork](https://help.github.com/articles/configuring-a-remote-for-a-fork/)
5. 포크 동기화 [Syncing a fork](https://help.github.com/articles/syncing-a-fork/)
6. 

```console
$ git clone git@github.com:YOUR_GITHUB_ACCOUNT/kakaobooks.git
$ cd kakaobooks
$ git remote add upstream git@github.com:jeedy/kakaobooks.git
$ git fetch upstream
$ git checkout master
$ git merge upstream/master
$ 
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
$ mvn clean compile
$ mvn test
```

### 배포용 (Jar 압축)

```
$ export JAVA_HOME="`/usr/libexec/java_home -v '1.8*'`"
$ mvn clean compile
$ mvn pakage
$ cd target
```
문의: <kk59491@gmail.com>