package ru.itmo.blss.firstlab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    public String login;
    public String password;
}
