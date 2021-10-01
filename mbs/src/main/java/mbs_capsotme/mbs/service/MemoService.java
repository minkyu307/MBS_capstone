package mbs_capsotme.mbs.service;

import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Long save(Memo memo) {
        memoRepository.save(memo);
        return memo.getId();
    }

    public List<Memo> findMemos() {
        return memoRepository.findALl();
    }

    public Optional<Memo> findOne(Long memoId) {
        return memoRepository.findById(memoId);
    }

    public List<Memo> findAllByMember(Member member){
        return memoRepository.findAllByMember(member);
    }

    public void deleteMemo(Long id){
        memoRepository.deleteMemo(id);
    }

    public void clearPersist() {
        memoRepository.clearPersist();
    }
}
