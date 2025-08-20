package org.example.expert.config;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
public class PasswordEncoderTest {

    @Test
    void matches_메서드가_정상적으로_동작한다(){
        //given
        PasswordEncoder encoder = new PasswordEncoder();
        String raw="MyP@ssw0rd";
        String encoded=encoder.encode(raw);
        //when
        boolean actual=encoder.matches(raw, encoded);
        //then
        assertThat(actual).isTrue();
    }
}
// 테스트 코드를 그냥 작성하면 무슨 상황에서 어떤 걸 검증하는지 헷갈릴 수 있음
// 그래서 Gicen-When-Then패턴을 쓰며 마치 스토리처럼 읽혀서 테스트의 의도를 한 눈에 알 수 있음
//Given-준비/상황 설정 "회원가입을 마친 상태다"
//When-행동/실행 "사용자가 로그인을 시도한다."
//Then-검증/결과 확인 "로그인에 성공해야 한다."