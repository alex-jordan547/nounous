package nounous.jsf.util;

import java.lang.reflect.Method;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;


@SuppressWarnings("rawtypes")
@ApplicationScoped
@Named
public class ConverterGetId implements Converter {
	
	
	// Actions	
	
	@Override
	public Object getAsObject( FacesContext fc, UIComponent uic, String value ) {
		
		if (value == null || value.isEmpty()) {
			return null;
		}

		List items = null;
		for( UIComponent c : uic.getChildren() ) {
			if ( c instanceof UISelectItems ) {
				items = (List) ( (UISelectItems) c ).getValue();
				break;
			}
		}
		
		for ( Object item :  items ) {
			try {
				Method method = item.getClass().getMethod( "getId" );
				if ( String.valueOf( method.invoke( item ) ) .equals( value ) ) {
					return item;
				}
			} catch ( ReflectiveOperationException | SecurityException | IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	@Override
	public String getAsString( FacesContext fc, UIComponent uic, Object value ) {
		if (value == null) {
            return "";
        }
		try {
			Method method = value.getClass().getMethod( "getId" );
			return  String.valueOf( method.invoke( value ) );
		} catch ( ReflectiveOperationException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
            return "";
		}
	}

}
