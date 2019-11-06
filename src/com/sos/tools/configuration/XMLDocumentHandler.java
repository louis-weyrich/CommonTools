package com.sos.tools.configuration;

import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ext.EntityResolver2;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;


public interface XMLDocumentHandler extends ErrorHandler, ContentHandler,
		EntityResolver, EntityResolver2, DTDHandler
{

}
