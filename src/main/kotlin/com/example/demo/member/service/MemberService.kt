package com.example.demo.member.service

import com.example.demo.member.dto.CreateMemberRequest
import com.example.demo.member.entity.Member
import com.example.demo.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {

    fun signUp(memberRequest: CreateMemberRequest): String {
        val loginId = memberRepository.findByLoginId(memberRequest.loginId)

        if (loginId != null) return "이미 등록된 ID입니다.";

        val member = Member(
            null,
            memberRequest.loginId,
            memberRequest.password,
            memberRequest.name,
            memberRequest.birthDate,
            memberRequest.gender,
            memberRequest.email
        )

        memberRepository.save(member);

        return "회원가입이 완료되었습니다."
    }
}