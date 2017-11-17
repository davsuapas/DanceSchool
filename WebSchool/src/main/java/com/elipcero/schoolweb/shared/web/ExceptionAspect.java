package com.elipcero.schoolweb.shared.web;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import feign.FeignException;

@Aspect
@Component
public class ExceptionAspect {
	
	private static final String ERROR_DESCRIPTION_ATTRIBUTE = "errorDescription";

	@Around("@annotation(ExceptionController)")
	public Object handleAccessToken(ProceedingJoinPoint thisJoinPoint) throws Throwable {
	
		MethodSignature methodSignature = (MethodSignature)thisJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ExceptionController exceptionController = method.getAnnotationsByType(ExceptionController.class)[0];
        
		try {
			return thisJoinPoint.proceed();
		}
		catch (FeignException ex) { 
			
			String message = getMessage(exceptionController, ex); 
			
			if (isRedirect(exceptionController.viewName())) {
				RedirectAttributes redirectAttr = (RedirectAttributes)Arrays.stream(thisJoinPoint.getArgs()).filter(o -> o instanceof RedirectAttributes).findFirst().get();
				redirectAttr.addFlashAttribute(ERROR_DESCRIPTION_ATTRIBUTE, message);
			}
			else {
				Model model = (Model)Arrays.stream(thisJoinPoint.getArgs()).filter(o -> o instanceof Model).findFirst().get();
				model.addAttribute(ERROR_DESCRIPTION_ATTRIBUTE, message);
			}
			
			return exceptionController.viewName();
		}
	}

	private static String getMessage(ExceptionController exceptionController, FeignException ex) {
		return Arrays.stream(exceptionController.messages())
				.filter(m -> Integer.parseInt(m.split(";")[0]) == ex.status())
				.findFirst()
				.orElse("0;Consulte con el administrador")
				.split(";")[1];
	}

	private static boolean isRedirect(String viewName) {
		return viewName.contains("redirect:");
	}	
}
