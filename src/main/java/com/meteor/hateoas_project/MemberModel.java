package com.meteor.hateoas_project;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.RepresentationModel;

public class MemberModel extends RepresentationModel<MemberModel> {

    public MemberModel (Member member) {
        this.member = member;
    }

    @JsonUnwrapped
    private final Member member;

}
