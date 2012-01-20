package com.popcorn.view;

import com.popcorn.view.utils.MessageProvider;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.springframework.binding.message.MessageResolver;

import org.springframework.stereotype.Component;

@Component
public class FacesHelper implements Serializable {

	/**
	 * This method can be used in a Facelets View to render a box around <h:messages/> conditionally.
	 * Note that this is only necessary with vanilla JSF. With PrimeFaces you can use <p:messages/>
	 * or <p:growl>.
	 * 
	 * @return true if there are messages to display
	 */
	public boolean isError() {
		return FacesContext.getCurrentInstance().getMessages().hasNext();
	}

        public  MessageResolver getInfoMessage(String varMessageBundle, Object... params) {
            return new org.springframework.binding.message.MessageBuilder().info().defaultText(MessageProvider.getValue(varMessageBundle, params) ).build();
    }
	
}
