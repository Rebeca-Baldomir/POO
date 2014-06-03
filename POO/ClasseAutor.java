public class AutorJDBC implements /*interface para o acesso do objeto*/{

	private static final String CONSULTA_PRODUCAO_AUTOR = 
			"SELECT F.ID, F.NOME, F.DIRETOR, F.GENERO, F.ENREDO, F.ANO  " +
	        "U.ID, U.NOME, U.EMAIL, " +
	        "FROM TB_FILME F, TB_USUARIO U";

	@Override
	public void salvarFilme(Filme filme) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();

		try {
			Connection con = manager.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("INSERT INTO TB_FILME(ID, NOME, DIRETOR, GENERO, ENREDO, ANO) 
														VALUES(?, ?, ?, ?, ?, ?)");

			ps.setLong	(1, autor.getId());
			ps.setString(2, autor.getNome());
			ps.setString(3, autor.getDiretor());
			ps.setString(3, autor.getGenero());
			ps.setString(3, autor.getEnredo());
			ps.setInt	(3, autor.getAno());

			ps.executeUpdate();
			con.commit();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public Autor pesquisaPorId(long id) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();

		try {
			Connection con = manager.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"SELECT ID, NOME, DIRETOR, GENERO, ENREDO, ANO " +
					"FROM TB_FILME " +
					"WHERE ID = ?");

			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) { //esperamos apenas uma linha
				String nome = rs.getString(2);
				String diretor = rs.getString(3);
				String genero = rs.getString(3);
				String enredo = rs.getString(3);
				int ano = rs.getString(3);
				con.close();
				return new Filme(id, nome, diretor, genero, enredo, ano);
			}
			else {
				con.close();
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void deletarFilme(long id) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();
		Connection con = null;
		try {
			con = manager.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps1 = con.prepareStatement(
					"DELETE FROM TB_USUARIO_has_FILMES" +
					"WHERE FK_FILME = ?");

			PreparedStatement ps2 = con.prepareStatement(
					"DELETE FROM TB_FILME WHERE ID = ?");

			ps1.setLong(1, id);
			ps1.executeUpdate();

			ps2.setLong(1, id);
			ps2.executeUpdate();

			con.commit();
			con.close();

		}catch(Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void salvarUsuario(long id, String nome, String email) throws DBException{
		JDBCManager manager = DAOFactory.instance().manager();
		Connection con = null;
		try {
			con = manager.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO TB_USUARIO " +
					"(ID, " +  
					"NOME," +  
				    "EMAIL, " + 
					"VALUES(?, ?, ?)");

			ps.setInt(1, id.getId());
			ps.setLong(2, nome.getNome);
			ps.setLong(3, email.getEmail);  

			ps.executeUpdate();
			con.commit();
			con.close();

		}

		catch(Exception e) {
			throw new DBException(e);
		}
	}

	@Override
	public Filme carregarFilmes(long id) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();
		Connection con = null; 

		try{
			con = manager.getConnection();
			PreparedStatement ps = con.prepareStatement(CONSULTA_FILMES_VISTOS);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				String nome = rs.getString(2);
				String diretor = rs.getString(3);
				String genero = rs.getString(3);
				String enredo = rs.getString(3);
				int ano = rs.getString(3);
				Filme filme = new Filme(id, nome, diretor, genero, enredo, ano);

				Long id = rs.getLong(4);
				String nome = rs.getString(5);
				String email = rs.getString(6);

				Usuario usuario = new Usuario(id, nome, email);

				filme.adicionaUsuario(usuario);

				while(rs.next()) {
					id = rs.getLong(4);
					nome= rs.getString(5);
					email = rs.getString(6);

					usuario = new Usuario(id, nome, email);

					filme.adicionaUsuario(usuario);

				}
				return filme;
			}
			return null;
		}
		catch(Exception e) {
			throw new DBException(e);
		}
		finally{
			try {
				con.close();
			}
			catch(Exception e) {
				throw new DBException(e);
			}
		}
	}

	@Override
	public Usuario carregarUsuarios(long id) throws DBException {
		JDBCManager manager = DAOFactory.instance().manager();
		Connection con = null; 

		try{
			con = manager.getConnection();
			PreparedStatement ps = con.prepareStatement(CONSULTA_USUARIOS_FILMES);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				String nome = rs.getString(2);
				String email = rs.getString(3);

				Usuario usuario = new Usuario(id, nome, email);

				Long id = rs.getLong(4);
				String nome = rs.getString(5);
				String diretor = rs.getString(6);
				String genero = rs.getString(6);
				String enredo = rs.getString(6);
				String ano = rs.getInt(6);

				Filme filme = new Filme(id, nome, diretor, genero, enredo, ano);

				usuario.adicionaFilme(filme);

				while(rs.next()) {
					id = rs.getLong(4);
					nome= rs.getString(5);
					diretor = rs.getString(6);
					genero = rs.getString(6);
					enredo = rs.getString(6);
					ano = rs.getInt(6);

					filme = new Filme(id, nome, diretor, genero, enredo, ano);

					usuario.adicionaFilme(filme);

				}
				return usuario;
			}
			return null;
		}
		catch(Exception e) {
			throw new DBException(e);
		}
		finally{
			try {
				con.close();
			}
			catch(Exception e) {
				throw new DBException(e);
			}
		}
	

}
