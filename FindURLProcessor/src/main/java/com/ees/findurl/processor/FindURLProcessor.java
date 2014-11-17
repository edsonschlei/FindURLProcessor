/**
 * 
 */
package com.ees.findurl.processor;

import java.util.Date;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.ees.findurl.FindURL;

/**
 * @author schlei
 *
 */
@SupportedAnnotationTypes("com.ees.findurl.FindURL")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class FindURLProcessor extends AbstractProcessor {

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
		messager.printMessage(Diagnostic.Kind.NOTE, "now: " + new Date());
        messager.printMessage(Diagnostic.Kind.NOTE, "annotations: " + annotations);
        messager.printMessage(Diagnostic.Kind.NOTE, "processing over: " + roundEnv.processingOver());
		
		for (Element elem : roundEnv.getElementsAnnotatedWith(FindURL.class)) {
			FindURL complexity = elem.getAnnotation(FindURL.class);
			
	        String message = "element Class: " + elem.getClass().getName()
	        		+ "\n enclosing (parent/class): " + elem.getEnclosingElement().toString()
	        		+ "\n kind: "+ elem.getKind().name()
	        		+ "\n annotation found in " + elem.getSimpleName()
                   + "\n with url " + complexity.url();
	        messager.printMessage(Diagnostic.Kind.NOTE, message);
	    }
//		error
//		processingEnv.get
	    return true; // no further processing of this annotation type
	}

	/**
	 * @param elem
	 * @return
	 */
    private String getElementClasspath(Element elem) {
    	Element current = elem.getEnclosingElement();
    	StringBuilder sb = new StringBuilder();
    	while(current != null) {
    		if (sb.length() > 0) {
    			sb.insert(0, ".");
    		}
    		sb.insert(0, current.toString());
    		current = current.getEnclosingElement();
    	}
	    return sb.toString();
    }

}
