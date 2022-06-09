package nounous.converter;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import nounous.jsf.data.Categorie;
import nounous.jsf.data.Enfant;

@Named
@RequestScoped
public class ConverterEnfant implements Converter<Enfant> {

	// Actions

	@SuppressWarnings("unchecked")
	@Override
	public Enfant getAsObject(FacesContext context, UIComponent uic, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}

		List<Enfant> items = null;
		for (UIComponent c : uic.getChildren()) {
			if (c instanceof UISelectItems) {
				items = (List<Enfant>) ((UISelectItems) c).getValue();
				break;
			}
		}

		var id = Integer.valueOf("x"+value);
		for (Enfant item : items) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Enfant item) {

		if (item == null) {
			return "";
		}
		return String.valueOf(item.getId());
	}
}
