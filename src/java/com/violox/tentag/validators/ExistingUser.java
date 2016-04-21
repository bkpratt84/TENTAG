package com.violox.tentag.validators;

import com.violox.tentag.controllers.AdminUserController;
import com.violox.tentag.domain.DbContext;
import com.violox.tentag.domain.User;
import java.util.logging.Logger;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("existingUser")
public class ExistingUser implements Validator {
    //private static final Logger logger = Logger.getLogger("ExistingUser");

    private final DbContext dbContext;
    private final AdminUserController ctrl;
    
    public ExistingUser() {
        this.dbContext = CDI.current().select(DbContext.class).get();
        this.ctrl = CDI.current().select(AdminUserController.class).get();
    }
    
    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        if (value != null && !value.toString().equals("")) {
            User u, temp;

            temp = new User();
            temp.setName(value.toString());

            u = (User) dbContext.User().getByAlternateKey(temp);
            
            if (u.getName() != null && !ctrl.getUser().equals(u) && temp.getName().toLowerCase().equals(u.getName().toLowerCase())) {
                FacesMessage msg = new FacesMessage(null, null);
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
   }
}