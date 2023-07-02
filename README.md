# saga-pattern-example

## 기능 요구사항

- 사용자가 상품을 주문할 수 있다.
- 상풍 결제가 실패하면 주문이 취소된다.
- 상품 결제가 실패하면 재고를 다시 증가시킨다.

## 개발 환경

- Java 17
- Spring Boot 3.1
- Gradle 8.1
- JPA
- Kafka

## 외부 라이브러리 목록

| Dependency                                            | Purpose                                       |
|-------------------------------------------------------|-----------------------------------------------|
| org.slf4j:slf4j-api                                   | SLF4J(Simple Logging Facade for Java)를 위한 API |
| ch.qos.logback:logback-classic                        | 로깅 프레임워크 Logback의 구현체                         |
| org.springframework.boot:spring-boot-starter-web      | Spring Boot 웹 애플리케이션 개발을 위한 스타터 의존성           |
| org.springframework.boot:spring-boot-starter-data-jpa | Spring Boot와 JPA를 사용하여 데이터베이스 액세스를 위한 스타터 의존성 |
| org.springframework.kafka:spring-kafka                | Spring과 Apache Kafka 통합을 위한 라이브러리             |
| com.h2database:h2                                     | H2 데이터베이스를 위한 의존성                             |
| org.springframework.boot:spring-boot-starter-test     | Spring Boot 애플리케이션의 테스트를 위한 스타터 의존성           |
| org.springframework.kafka:spring-kafka-test           | Spring Kafka 애플리케이션의 테스트를 위한 스타터 의존성          |

## Challenge

### 분산 트랜잭션

분산 트랜잭션은 여러 개의 서비스가 하나의 트랜잭션으로 동작하도록 하는 것을 말한다. Saga 패턴은 분산 트랜잭션을 사용하여 마이크로서비스 간의 데이터 일관성을 유지한다.

### Saga 패턴

Saga 패턴은 여러 개의 마이크로서비스 간의 트랜잭션을 관리하는 패턴이다. Saga 패턴은 마이크로서비스 간의 트랜잭션을 관리하기 위해 2가지 방법을 제공한다.

- Choreography-based Saga
- Orchestration-based Saga

해당 프로젝트는 Choreography-based Saga를 사용한다.

`Order -> Stock -> Payment` 순으로 트랜잭션을 처리한다. 각각의 서비스는 Kafka를 통해 이벤트를 발행하고, 이벤트를 구독하여 트랜잭션을 처리한다.
Payment 서비스에서 결제가 실패하면, Stock 서비스에서 재고를 증가시키고, Order 서비스에서 주문을 취소한다.

## TO-BE

- 단순한 예제로 끝나는 것이 아니라, 실제 비즈니스 모델과 유사한 예제로 구현해보자.

## API 명세

### 주문 생성 API (order-app)
```
POST /orders
```

> Request

```json
{
  "productId": 1
}
```

| 필드명       | 타입   | 설명     | 필수 여부
|-----------|------|--------| --- |
| productId | Long | 상품 아이디 | O |

## 다운로드 및 실행

1. 도커 컨테이너 실행 (docker-compose up -d)
2. 각 서비스 실행