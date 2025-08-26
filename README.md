# 과제
**모든 내용을 한 번에 완벽하게 이해하려 하지 않아도 괜찮습니다.**

---

- 여러분의 속도에 맞춰, 나만의 학습 목표를 세우며 차근차근 나아가시길 바랍니다.
- 조금 더 넓고 길게 보는 마음으로 여러분만의 속도로 걸어가 주세요.
- 아래는 수료 시점에 도달했을 때 여러분이 성취하길 바라는 목표를 정리한 내용입니다.
- 경쟁력은 본인만의 강점을 강화하는 데서 이루어집니다.
- 아래 예시를 참고하여 학습 과정을 점검하거나 학습 계획을 세워 보세요.

---

### Spring Advanced – 과제 기록 </br>


  >과제 진행 방법

fork → clone → 로컬 개발

단계별로 커밋 메시지에 과제 번호 기록

```java
예) 레벨 1-1: Early Return 리팩토링
```

---

## Lv 1. 코드 개선 </br>
>1-1) Early Return – AuthService#signup

- 이메일 중복 체크를 맨 위로 올려 불필요한 encode() 실행 방지

> 1-2) 불필요한 if-else 피하기 (WeatherClient#getTodayWeather)

- 실패 케이스는 즉시 throw 하고, 성공 흐름만 남긴다.

> 1-3) Validation을 DTO로 이동 (UserService#changePassword)

- 서비스에 있는 형식 검증을 요청 DTO + Bean Validation으로 이동.


## Lv 2. N+1 문제 해결

- 기존 fetch join 기반 → @EntityGraph로 변경

> Lv 3. 테스트코드 연습

> 3-1) PassEncoderTest – matches 정상 동작

> 3-2) ManagerServiceTest – 예외/메서드명 일치

힌트: 실제 서비스가 던지는 예외 타입/메시지와 테스트 기대값을 일치시켜야 함.

메서드명도 실제 예외에 맞게 변경 </br>
(예: ..._InvalidRequestException_던진다()).

> 3-3) CommentServiceTest – “할일을 찾지 못함” 케이스 수정

todoId로 조회 실패 시, 실제 서비스 로직과 테스트 기대를 맞춤.

---

<a href="https://winwin0219.tistory.com/entry/Spring-Trouble-Gradle">트러블슈팅</a>



