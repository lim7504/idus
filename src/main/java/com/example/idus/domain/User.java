package com.example.idus.domain;

import com.example.idus.domain.dto.GetOrderDto;
import com.example.idus.domain.dto.GetUserDto;
import com.example.idus.domain.dto.JoinUserDto;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

import static java.util.Comparator.comparing;

@Getter
@Entity
@Table(name = "idus_user")
public class User extends CreatedModifiedAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private String password;

    private String mobile;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    public static User createUser(JoinUserDto joinUserDto) {
        User user = new User();
        user.name = joinUserDto.getName();
        user.nickname = joinUserDto.getNickname();
        user.password = joinUserDto.getPassword();
        user.mobile = joinUserDto.getMobile();
        user.email = joinUserDto.getEmail();
        user.gender = joinUserDto.getGender();
        return user;
    }

    public GetUserDto getUserDto() {
        return GetUserDto.builder()
                .id(this.id)
                .name(this.name)
                .nickname(this.nickname)
                .mobile(this.mobile)
                .password(this.password)
                .email(this.email)
                .gender(this.gender)
                .build();
    }

    public GetUserDto getUserDtoForList() {
        GetOrderDto lastOrder = orderList.stream()
                .max(comparing(Order::getCreatedDate))
                .map(Order::GetOrderListDto)
                .orElse(null);

        return GetUserDto.builder()
                .id(this.id)
                .name(this.name)
                .nickname(this.nickname)
                .mobile(this.mobile)
                .password(this.password)
                .email(this.email)
                .gender(this.gender)
                .lastOrder(lastOrder)
                .build();
    }
}
