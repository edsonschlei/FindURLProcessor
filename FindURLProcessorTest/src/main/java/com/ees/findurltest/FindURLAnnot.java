/**
 * 
 */
package com.ees.findurltest;

import com.ees.findurl.FindURL;

/**
 * @author schlei
 *
 */
public class FindURLAnnot {

	@FindURL(url="/api/find/url/test")
	public String foundURL() {
		System.out.println("found URL");
		return "";
	}
	
}
