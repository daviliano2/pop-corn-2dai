package popcorn.dao;

import org.springframework.stereotype.Repository;
import popcorn.persistence.Usuario;

@Repository
public class UsuarioDAOImpl extends GenericPopDAOImpl<Usuario, String> implements UsuarioDAO {
	
}