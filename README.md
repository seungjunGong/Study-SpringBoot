## SpringBoot 기초 학습 페이지
spring 부트 학습시 노션에서 정리한 것을 기록합니다. 

# Spring

- Spring: Java 웹 애플리케이션을 개발 할 수 있는 Framework이다.

  Spring은 객체를 결합시키고 의존성을 부여하는 여러 일들을 수행 해준다.

  JVM에서 Spring 컨텍스트를 실행시켜야함.

  spring에서 관리하는 것들을 Spring Bean 이라고 함.

  record -> 세터 게터 생성자를 자동으로 만드들어주는 클래스

  Spring Boot: Spring을 보다 쉽게 사용할 수 있도록 돕는 도구이다.

    - 배울게 많다. 그러나 코드를 줄일 수 있다.

## Spring 컨테이너(= Spring Context, IOC 컨테이너)

spring Bean과 생명주기를 관리한다.

java클래스와 Configuration 파일로 구성

spring context 에서 Bean을 관리함

## Spring 컨테이너의 종류

1. Bean Factiory: 기본적인 Spring Container
2. Application Context: 향상된 Spring 컨테이너 기업 전용 기능 존재(주로 사용)



## Bean(스프링 컨테이너에서 관리하는 객체)

POJO
우리가 일반적으로 알고 있는 java 객체

Java Bean(자주 사용 x)

조건

1. 기본 생성자 존재
2. 게터, 세터 존재
3. Serializable 인터페이스 선언

Spring Bean(자주 사용)

Spring이 관리하는 객체

---

클래스를 Bean으로 등록하는 방법

```java
@Component
public class MyBean {
}
```

## IoC, DI

IoC(Inversion of Control, 제어의 역전) : 객체를 직접 생성해서 제어하는 것이 아닌 외부에서 객체를 가져와 사용하는 것을 말함. 스프링은 스프링 컨테이너가 객체를 관리, 제공하는 역할을 함.

DI(Dependency Injection, 의존성 주입) : 어떤 클래스가 다른 클래스에 의존함. 다른 클래스에서 객체를 만들어서 이를 어떤 클래스에 전달(주입)해줌

# Spring Boot

## 자동 구성

스프링 부트에서 서버를 시작할 때 구성 파일을 읽어와서 설정함.

META-INF에 있는 spring.factories 파일에 존재

External Libarries → spring-boot-autoconfigure → META_INF, autoconfigure

- 자동 구성 클래스는 AutoConfiguration, 속성값 클래스는 Properties 가 끝에 붙음.
- 빈이 자동으로 등록되고 구성됨.

## 빈에서 사용하는 애너테이션

아래 애너테이션 모두 `Component`를 가짐.

`@Configuration` : 설정 파일 등록

`@Repository` : ORM 매핑

`@Controller, @RestController` : 라우터

- `RestController` 의 경우 `Controller` 와 `ResponseBody` 가짐.
- `Contoller` 는 `Component` 를 가짐

`@Service` : 비즈니스 로직

## SpringBootApplication

`@SpringBootConfiguration` : 스프링 부트 관련 설정을 나타내는 애너테이션, 스프링의 `@Configuration`을 상속해서 만든 애너테이션

`@Componentscan` : 사용자가 등록한 빈을 읽고 등록하는 애너테이션 `@Component` 애너테이션을 가진 클래스를 찾아 빈으로 등록함.

→ `Component` 어노테이션이 있으면 해당 클래스는 빈이 된다.

`@EnableAutoConfiguration` : 스프링 부트에서 자동 구성을 활성화하는 애너테이션, 메타파일을 읽고 자동으로 정의된 설정을 구성함.

## 스프링 부트 계층

작업 구성

DTO 작성 → 서비스(내부로직) 작성 → 컨트롤러(호출로직) 작성 → 테스트 코드 작성

### 프레젠테이션 계층

HTTP 요청을 받고 비즈니스 계층으로 전송하는 역할

컨트롤러가 담당 - 데이터 받아오는 클래스

메소드 정리

@GetMapping, @PostMapping, @PutMapping

- @DeleteMapping

### 비즈니스 계층

모든 비즈니스 로직 처리

데이터 처리에 대한 로직을 여기서 작성한다.

DTO(Data Transfer Object) 데이터를 교환하기 위해 사용하는 개체를 작성

서비스가 담당 - 로직 클레스

### 퍼시스턴스 계층

데이터 베이스 관련 로직 처리, DAO 데이터 저장 하는 객체를 사용한다.

리포지토리가 담당 - 데이터 저장 클래스, DB 연동 레포지토리 인터페이스 사용

## 디렉토리 구성

src

- main : 실제 코드를 작성하는 공간
    - java
    - resources
        - templates : HTML 파일과 같은 뷰 관련 파일
        - static : JS, CSS, 이미지 같은 정적 파일
        - application.yml : 스프링 부트 설정 파일(자동 로딩) 데이터 베이스, 로깅 설정

          show-sql, format_sql 실행 구문 보여주는 옵션

          defer-datasource-initialization 테이블 생성 및 dqta.sql 파일 쿼리 실행 옵션

- test : 테스트 코드와 리소스 파일

build.gradle : 빌드 설정 파일, 의존성이나 플러그인 설정시 사용

settings.gradle : 빌드할 프로젝트의 정보를 설정하는 파일

# 테스트 코드

## 스프링 부트 테스트

spring-boot-starter-test 에 테스트 도구가 있다.

- JUnit : 자바 언어용 단위 테스트 프레임워크
- AssertJ : 어설션 작성시 사용되는 라이브러리

### JUnit

자바 언어를 위한 단위 테스트 프레임워크

특징 테스트 방식을 구분하는 애너테이션 제공

`@Test` 애너테이션으로 메서드 호출시마다 새 인스턴스를 생성한다.

메소드 정리

`@BeforeAll // 전체 테스트를 시작하기 전에 1회 실행함. 메서드는 static으로 선언`

데이터 베이스 연결 및 테스트 환경 초기화시에 사용

`@BeforeEach // 테스트 케이스 시작하기 전마다 실행`

테스트에서 사용하는 객체를 초기하거나 테스트에 필요한 값을 넣을 때 사용

`@AfterAll // 전체 테스트를 마치고 종료하기 전에 1회 실행, static으로 선언`

데이터 베이스 연결 종료, 공통적으로 사용하는 자원해제 시 사용

`@AfterEach // 종료하기 전마다 실행`

테스트 이후 특정 데이터 삭제시 사용

### AssertJ

검증문의 가독성을 높여주는 라이브러리

```java
Assertions.assertEquals(sum, a + b); // 가독성이 떨어짐

assertThat(a + b).isEqualTo(sum); // 훨씬 가독성이 좋다.
```

필요시 메소드 검색후 사용

## 애너테이션

`@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성`

`@SpringBootApplication` 클래스를 찾고 해당 클래스에 포함되어 있는 빈을 찾은 후 테스트용 애플리케이션 컨텍스트를 생성함

`@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성`

MockMvc를 생성하고 자동으로 구성하는 애너테이션

- MockMvc 란?

  실제 객체와 비슷하지만 테스트에 필요한 기능만 가지는 가짜 객체를 만들어서 애플리케이션 서버에 배포하지 않고도 스프링 MVC 동작을 재현할 수 있는 클래스

  [SpringBoot의 MockMvc를 사용하여 GET, POST 응답 테스트하기](https://shinsunyoung.tistory.com/52)


## given-when-then 패턴

given: 테스트 실행 준비

- 변수설정, 멤버 저장

when: 테스트 진행

- 값 저장 및 로직 실행, api 호출

then : 테스트 결과 검증

- 결과 검증, 응답코드 값 확인

# 데이터베이스

H2 : 자바로 작성 되어 있는 RDBMS. 스프링 부트가 지원하는 인메모리 관계형 데이터 베이스

테스트 용도로 주로 사용한다.

애너테이션

`@Transactional` 메서드를 하나의 트랜잭션으로 묶는 역할을 하는 애너테이션

update 문 작성시 일관성을 유지하기 위해 사용

## JPA(Java Persistence API)

자바에서 관계형 데이터 베이스를 사용하는 방식을 정의한 인터페이스

### 엔티티

데이터베이스 테이블과 매핑되는 객체. 쿼리를 실행하는 객체로 보자.

### 엔티티 매니저

엔티티를 관리해 데이터베이스와 애플리케이션 사이에 객체를 CRUD 역할을 함.

앤티티 매니저 팩토리에서 앤티티 매니저를 만듦.

스프링 부트에서는 `@PersistenceContext` 또는 `@Autowired` 애너테이션 사용

프록시 매너지를 사용하다가 필요시 실제 앤티티 매니저를 호출함.

### 영속성 컨텍스트

앤티티를 관리하는 가상의 공간

**1차 캐시**

앤티티 조회시 1차캐시에서 먼저 조회

캐시의 키: `@Id` 애너테이션

**쓰기 지연**

트랜잭션 커밋 전까지 질의문을 보내지 않고 커밋시에 한번에 실행하는 것

**변경 감지**

트랜잭션 커밋시 1차 캐시에 저장되어 있는 값과 현재 엔티티 값을 비교해서 변경하고 데이터 베이스에 반영

**지연 로딩**

바로 로딩 하는 것이 아닌 필요 시에 쿼리를 날려 데이터를 조회

### 앤티티 상태

분리: 앤티티가 생성되고 영속성 컨텍스트가 관리하고 있지 않는 상태

관리: 영속성 컨텍스트가 관리하는 상태

비영속: 영속성 컨텍스트와 전혀 관계 없는 상태

삭제: 엔티티가 삭제된 상태

### ORM(Object-Relational mapping)

자바 객체와 데이터 베이스를 연결하는 프로그래밍 기법

장점:

sql을 직접 작성하지 않고 데이터 베이스 접근 가능.

객체 지향코드로 작성 할 수 있어 비즈니스 로직에 집중 가능.

MySQL → PostgreSQL 전환 가능 데이터 베이스 시스템 종속성이 줄음

ERD에 대한 의존도를 낮춤?

단점:

프로젝트가 커질 수록 사용 난이도가 올라감

복잡하고 무거운 쿼리는 ORM으로 해결 불가능

ORM 프레임워크로 JPA를 구현하여 사용한다.

대표적 JPA 구현체로 하이버네이트 가 있다. JDBC API 사용해 데이터베이스 종류와 상관 없이 사용 가능

### 스프링 데이터 JPA

스프링 데이터: 데이터 베이스 사용 기능을 클래스 레벨에서 추상화(인터페이스를 통해 사용)

스프링 데이터 JPA(JpaRepository): 스프링 데이터 인터페이스(PagingAndSortingRepository) + JPA 기능

JpaRepository<Member, Long>, <엔티티이름, 엔티티기본타입>을 인터페이스에 상속 받아 사용

- JpaRepository는 CrudRepository를 상속받는데 해당 클래스를 `save()` 를 호출하면

  데이터베이스에 엔티티를 저장한다.


---

# Java 학습

애너테이션 : 자바 소스 코드에 추가하는 표식, JDK 1.5 버전 부터 사용 가능, 메타 데이터의 목적으로 사용

롬복: 반복 메소드 작성 코드를 줄여주는 라이브러리

여러가지 애너테이션으로 getter, setter, toString 등 메소드를 자동으로 만들어준다.

코드를 반복해 입력할 필요가 없어 가독성이 향상된다.

- 빌더 패턴: 필드에 어떤값이 들어가는지 한눈에 알 수 있다.
    - 코드

        ```java
            @Builder // 빌더 패턴으로 객체 생성(롬복에서 지원)
            public Article(String title, String content){
                this.title = title;
                this.content = content;
            }
            /*
            빌더 패턴
            Article.builder()
                .title("abc")
                .content("def")
                .build();
             */
        ```

- `Getter` 애너테이션
  자동으로 getter 코드를 작성해준다.
- `@NoArgsConstructor` 애너테이션

  기본 생성자를 자동으로 반영해준다.
  `@NoArgsConstructor(access = AccessLevel.*PROTECTED*)`

  == `protected Article(){ // 기본 생성자}`

- `@RequiredArgsConstructor`

  final 혹은 @NotNull이 붙은 생성자를 만들어줌.


# REST API

REST(REpresentational State Transfer) API

자원을 이름으로 구분해 자원의 상태를 주고 받는 API 방식

URL의 설계 방식을 말한다.

**특징**

서버/클라이언트 구조, 무상태, 캐시 처리 기능, 계층화, 인터페이스 일관성

**장점**

URL 만 보고도 무슨 행동을 하는 API 인지 명확하게 알 수 있다.

HTTP 표준을 사용하는 모든 플랫폼에서 사용 가능하다.

**단점**

GET, POST 방식의 제한이 있고, 설계시 공식적으로 제공되는 표준 규약이 없다.

- 사용방법(규칙)
    1. URL에 동사를 쓰지 말고, 자원을 표시한다.
       `./get-student?student_id=1` (x)
    2. 동사는 HTTP 메서드로 표현한다.
       HTTP 메소드 CRUD
       `POST` : 만들기
       `GET` : 읽기
       `PUT` : 업데이트
       `DELETE` : 삭제
- 응답 코드

  200 OK : 요청이 성공적으로 수행

  201 Created : 요청 성공적으로 수행, 새로운 리소스 생성

  400 Bad Request : 잘못된 요청

  403 Forbidden : 권한이 없음

  404 Not Found : 요청값으로 찾은 리소스가 없어 요청 실패

  500 Internal Server Error : 서버상 문제


# 용어 정리

라우터 : 컴퓨터 네트워크 간에 데이터 패킷을 전송하는 네트워크 장치

DAO : 데이터 베이스 계층과 상호작용하기 위한 객체

- WAS(Web Application Server) : DB 조회나 다양한 로직 처리를 요구하는 동적인 컨텐츠를 제공하기 위해 만들어진 서버

  HTTP 를 통해 컴퓨터 장치와 소통하는 미들웨어(소프트웨어 엔진)

  webserver(ex. apache) 와는 다르다고 한다. WAS(ex. tomcat) 는 동적인 웹을 지원

  서버 부하를 방지 하기 위해 정적인 컨텐츠는 webserver에서 지원하는 것이 좋음

  참고 : [https://gmlwjd9405.github.io/2018/10/27/webserver-vs-was.html](https://gmlwjd9405.github.io/2018/10/27/webserver-vs-was.html)


뷰 리졸버: 템플릿 엔진을 사용해 HTML 문서 JSON 데이터 생성

단위 테스트 : 작은 단위(메소드 단위)로 코드를 검증하는 것

JDBC(Java DataBase Connectivity): 자바에서 데이터 베이스를 종류와 관계없이 접속할 수 있게 하는 api 규격

직렬화, 역직렬화: 객체 데이터를 (csv, json) 포맷으로 만드는 것을 직렬화 반대로 (csv, json) 형태에서 객체로 변환하는 것을 역직렬화라고 함.

트랜잭션 : 데이터베이스의 질의 연산을 묶은 작업의 단위

[스프링 입문 정리](https://www.notion.so/d7c6467ee2104727ab704c5fc06e13f9?pvs=21)