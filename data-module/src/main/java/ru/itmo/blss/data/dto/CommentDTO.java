package ru.itmo.blss.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {
    public String payload;
    public int authorId;
}
