package com.founderz.auth.application.impl;

import com.founderz.auth.application.AuthReadService;
import com.founderz.common.exception.DuplicateException;
import com.founderz.common.vo.user.AccountId;
import com.founderz.common.vo.user.PhoneNumber;
import com.founderz.sms.application.SMSReadService;
import com.founderz.user.domain.UserDomainReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AuthReadServiceImpl implements AuthReadService {
    private final UserDomainReader reader;
    private final SMSReadService smsService;

    @Override
    public boolean isRegisteredAccount(final AccountId accountId) {
        return reader.existsByAccountId(accountId);
    }

    @Override
    public boolean sendSMS(final PhoneNumber tel) {
        if(reader.existsByTel(tel)) {
            throw new DuplicateException("이미 가입된 전화번호입니다.");
        }

        return smsService.send(tel);
    }

    @Override
    public boolean isVerify(final PhoneNumber tel, final String randomNum) {
        return smsService.verifySms(tel, randomNum);
    }
}
