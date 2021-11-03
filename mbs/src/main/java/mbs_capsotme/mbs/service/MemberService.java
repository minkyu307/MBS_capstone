package mbs_capsotme.mbs.service;

import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.MemberDetail;
import mbs_capsotme.mbs.repository.MemberRepository;
import mbs_capsotme.mbs.securityComponent.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long joinAndSave(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public MemberDetail loadMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .map(m->new MemberDetail(m, Collections.singleton(new SimpleGrantedAuthority(m.getRole().getValue())))).orElseThrow(()-> new UserNotFoundException(loginId));
    }

    /*public void addWebSocketSessionIdById(String id, String sessionId){
        memberRepository.addSessionIdById(id,sessionId);
    }

    public void deleteWebSocketSessionIdById(String id) {
        memberRepository.deleteSessionIdById(id);
    }*/

    public List<Member> findMembersByDepartmentId(Long id){

        return memberRepository.findMembersByDepartmentId(id);
    }

    public void deleteMember(Long id){
        memberRepository.deleteMember(id);
    }

    public void clearPersist() {
        memberRepository.clearPersist();
    }
}
