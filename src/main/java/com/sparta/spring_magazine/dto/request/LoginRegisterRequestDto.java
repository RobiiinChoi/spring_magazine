package com.sparta.spring_magazine.dto.request;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRegisterRequestDto {
    @NotEmpty(message ="아이디를 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "3글자 이상, 영어와 숫자로만 사용 가능합니다")
    private String username;

    @NotEmpty(message ="닉네임을 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$", message = "닉네임은 한글, 영어, 숫자만 사용가능합니다")
    private String nickname;

    @Size(min = 4, message = "비밀번호는 최소 4글자 이상이어야 합니다")
    private String password;

    @NotEmpty(message = "확인 비밀번호를 입력해주세요")
    private String checkPw;

    @AssertTrue(message = "비밀번호에 아이디와 같은 값은 들어가면 안됩니다")
    public boolean isContaionUsername() {
        return !password.contains(username);
    }

    @AssertTrue(message = "입력한 비밀번호와 같지 않습니다")
    public boolean isSamePwd() {
        return password.equals(checkPw);
    }
}
