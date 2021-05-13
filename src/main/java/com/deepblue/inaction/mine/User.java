package com.deepblue.inaction.mine;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username = "YangYang";

    private String password;

    public static String getName() {
        return new User().getUsername();
    }

    static {
        System.out.println("username = yangjianwei");
    }


    {
        username = "YangYang0001";
    }

}
