package tea.tech.teablog.dto;

import lombok.Data;

@Data
public class PostRequest {

    private String title;
    private String body;
    private String author;
}
