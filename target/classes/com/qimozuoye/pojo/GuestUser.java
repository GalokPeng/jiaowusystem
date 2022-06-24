package com.qimozuoye.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestUser implements Serializable {
    private int id;
    @NotBlank(message = "请输入用户名！")
    private String username;
    @NotBlank(message = "请输入密码！")
    private String password;
    private String perms;

    public GuestUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public GuestUser(@NotBlank(message = "请输入用户名！") String username, @NotBlank(message = "请输入密码！") String password, String perms) {
        this.username = username;
        this.password = password;
        this.perms = perms;
    }
}
