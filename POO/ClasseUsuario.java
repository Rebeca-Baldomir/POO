@Table(name="TB_USUARIO")
@Entity
public class Usuario {
	@Id
	@Column(name="ID")
	private Long id;

	@Column(name="NOME")
	private String nome;

	@Column(name="EMAIL")
	private String email;

	//Rola a treta de OnetoMany ?

	public Usuario (long id, String nome, String email) {
		this();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}