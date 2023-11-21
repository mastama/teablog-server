package tea.tech.teablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tea.tech.teablog.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
