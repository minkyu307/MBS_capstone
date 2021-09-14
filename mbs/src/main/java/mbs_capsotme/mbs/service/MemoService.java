package mbs_capsotme.mbs.service;

import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Long join(Memo memo) {
        memoRepository.save(memo);
        return memo.getId();
    }
}
