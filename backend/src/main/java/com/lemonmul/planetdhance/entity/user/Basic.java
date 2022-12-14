package com.lemonmul.planetdhance.entity.user;

import com.lemonmul.planetdhance.entity.Nation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Basic extends User{

    private String pwd;

    public Basic(String email, String nickname, String introduce, String imgUrl, Nation nation, Role role) {
        super(email, nickname, introduce, imgUrl, nation, role);
    }

    //==생성 메서드==//
    public static Basic createBasic(String email, String nickname,String introduce,String imgUrl,Nation nation, Role role, String pwd){
        Basic basic = new Basic(email, nickname, introduce, imgUrl, nation, role);
        basic.pwd = pwd;
        return basic;
    }
}
