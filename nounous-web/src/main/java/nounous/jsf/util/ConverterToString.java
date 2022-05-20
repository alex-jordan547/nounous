package nounous.jsf.util;

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
public class ConverterToString implements Converter {
	
	
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
			if (item.toString().equals( value ) ) {
				return item;
			}
		}
		return null;
	}

	
	@Override
	public String getAsString( FacesContext fc, UIComponent uic, Object value ) {

		if (value == null) {
            return "";
        }

		return value.toString();
	}

}
