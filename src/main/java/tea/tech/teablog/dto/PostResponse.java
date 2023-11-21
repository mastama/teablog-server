package tea.tech.teablog.dto;

import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String body;
    private String author;
}
