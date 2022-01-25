package com.meteor.hateoas_project;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.IdentityHashMap;
import java.util.Map;

@RestController // file이 아닌 Data를 반환함
@RequestMapping(value = "/api/member", produces = MediaTypes.HAL_FORMS_JSON_VALUE)
public class MemberController {

    private Map<Integer, Member> db = new IdentityHashMap<>();
    private Integer id = 1;

    @PostMapping
    public ResponseEntity createMember (@RequestBody Member member) {
        member.setId(id++);

        /*
            /api/member
         */
        WebMvcLinkBuilder listLink = WebMvcLinkBuilder.linkTo(MemberController.class);

        /*
            /api/member/{id}
         */
        WebMvcLinkBuilder selfLink = listLink.slash(member.getId());
        // hateoas model 객체 생성
        MemberModel memberModel = new MemberModel(member);
        // list link
        memberModel.add(listLink.withRel("list"));
        // self link
        memberModel.add(selfLink.withSelfRel());
        // update link
        memberModel.add(selfLink.withRel("update"));
        return ResponseEntity.created(selfLink.toUri()).body(memberModel);
    }

}
