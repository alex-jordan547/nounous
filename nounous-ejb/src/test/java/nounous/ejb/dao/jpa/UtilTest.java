package nounous.ejb.dao.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class UtilTest {


	@PersistenceContext( unitName = "nounous" )
	private EntityManager	em;
	
	
	public  void initDb( InputStream in ) {
		try {
			var br = new BufferedReader( new InputStreamReader( in ) );
			var sb = new StringBuilder();
			String st;
			while ((st = br.readLine()) != null) {
				if ( ! st.trim().startsWith( "--" ) ) {
					sb.append(st);
				}
				if ( st.trim().endsWith(";") ) {
					var query = em.createNativeQuery( sb.toString() );
					query.executeUpdate();
					sb.setLength(0);
				};
			}
			br.close();
			
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (Exception e) {
			throw new RuntimeException( e );
		}
		
	}

	
	public void initDb( String path ) {
		var in = UtilTest.class.getResourceAsStream( path );
		initDb( in );
	}

	
	public void initDb() {
		var in = UtilTest.class.getResourceAsStream("/3-data.sql");
		initDb( in );
	}
	
	
}
