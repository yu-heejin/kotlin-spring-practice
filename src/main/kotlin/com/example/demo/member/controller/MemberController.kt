package com.example.demo.member.controller

import com.example.demo.member.dto.CreateMemberRequest
import com.example.demo.member.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(private val memberService: MemberService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody memberRequest: CreateMemberRequest): String {
        return memberService.signUp(memberRequest)
    }
}