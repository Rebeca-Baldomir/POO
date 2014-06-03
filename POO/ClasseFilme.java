@Table(name="TB_FILME")
@Entity
public class Filme {
	@Id
	@Column(name="ID")
	private Long id;

	@Column(name="NOME")
	private String nome;

	@Column(name="DIRETOR")
	private String diretor;

	@Column(name="GENERO")
	private String genero;

	@Column(name="ENREDO")
	private String enredo;

	@Column(name="ANO")
	private int ano;

	//Rola a treta de OnetoMany ?

	public Filme (long id, String nome, String diretor, String genero, String enredo, int ano) {
		this();
		this.id = id;
		this.nome = nome;
		this.diretor = diretor;
		this.genero = genero;
		this.enredo = enredo;
		this.ano = ano;
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

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEnredo() {
		return enredo;
	}

	public void setEnredo(String enredo) {
		this.enredo = enredo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}