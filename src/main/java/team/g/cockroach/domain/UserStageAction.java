package team.g.cockroach.domain;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserStageAction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
}
