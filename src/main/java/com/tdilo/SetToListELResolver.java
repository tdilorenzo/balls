package com.tdilo;

import javax.el.ELContext;
import javax.el.ListELResolver;
import java.util.ArrayList;
import java.util.Set;


public class SetToListELResolver extends ListELResolver {

    public static final String KEY_PROPERTY = "setToList";

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        if (base instanceof Set<?> && KEY_PROPERTY.equals(property)) {
            context.setPropertyResolved(true);
            ArrayList<Object> list;
            list = new ArrayList<Object>((Set<?>) base);
            return list;
        }

        return super.getValue(context, base, property);
    }

}
