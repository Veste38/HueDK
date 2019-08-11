package be.sleroy.huedk;

import java.util.List;

import be.sleroy.huedk.dto.HueElement;
import be.sleroy.huedk.exception.HueDKException;

public abstract interface HueDKEventListener {

	abstract void onSignUp(String userId);
	
	abstract void onListReceived(Class<? extends HueElement> classType, List<? extends HueElement> list);

	abstract void onElementReceived(Class<? extends HueElement> classType, HueElement element);
	
	abstract void onError(Class<? extends HueDKException> exceptionType, HueDKException exception);

	abstract void onConnectionSucceed(String message);

}
