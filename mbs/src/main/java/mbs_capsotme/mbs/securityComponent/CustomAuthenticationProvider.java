package mbs_capsotme.mbs.securityComponent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mbs_capsotme.mbs.domain.MemberDetail;
import mbs_capsotme.mbs.service.MemberService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {

        //AuthenticationFilter 에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함.
        UsernamePasswordAuthenticationToken token=(UsernamePasswordAuthenticationToken) authentication;
        String login_id = token.getName();
        String password = (String)token.getCredentials();

        //MemberService를 통해 DB에서 아이디로 사용자 조회
        MemberDetail memberDetail = (MemberDetail) memberService.loadMemberByLoginId(login_id);

        if(!passwordEncoder.matches(password,memberDetail.getPassword())){
            throw new BadCredentialsException(memberDetail.getLogin_id()+"아이디 또는 비밀번호가 잘못되었습니다.");
        }

        return new UsernamePasswordAuthenticationToken(memberDetail,password,memberDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
