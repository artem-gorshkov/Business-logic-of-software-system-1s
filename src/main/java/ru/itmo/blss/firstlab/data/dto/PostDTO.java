package ru.itmo.blss.firstlab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {
    public String title;
    public String payload;
    public int authorId;
}
