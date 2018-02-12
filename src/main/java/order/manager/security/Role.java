package order.manager.security;

import lombok.Data;

/**
 * Created by tachen on 2018/2/11.
 */
@Data
public class Role {
    private String username;
    private String authority;
}