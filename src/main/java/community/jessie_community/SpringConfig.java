package community.jessie_community;

import community.jessie_community.repository.MemberRepository;
import community.jessie_community.repository.MemoryMemberRepository;
import community.jessie_community.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
