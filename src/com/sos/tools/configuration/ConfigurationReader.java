/**
 * 
 */
package com.sos.tools.configuration;

import java.io.File;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.sos.tools.configuration.data.AbstractConfigurationValue;
import com.sos.tools.configuration.data.ListValue;
import com.sos.tools.configuration.data.MappedValue;
import com.sos.tools.configuration.data.NamedValue;
import com.sos.tools.configuration.data.PrimitiveValue;
import com.sos.tools.configuration.data.StringValue;
import com.sos.tools.configuration.data.TypedValue;
import com.sos.tools.configuration.data.Unit;
import com.sos.tools.configuration.data.UnitValue;
import com.sos.tools.configuration.data.Value;
import com.sos.tools.logging.LoggerSession;
import com.sos.tools.utilities.collection.MappedMaps;
import com.sos.tools.utilities.collection.Stack;
import com.sos.tools.utilities.collection.UtilStack;


/**
 * @author louis.weyrich
 *
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class ConfigurationReader implements XMLDocumentHandler 
{
	
	LoggerSession logger = null;
	private Stack <Object> stack;
	private MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> configMap = null;
    private String currentConfigName = null;
    private boolean finished = false;
    private boolean isReadyForText = false;

   
	private Locator locator;

	/**
	 * 
	 */
	public ConfigurationReader() 
	{
		stack = new UtilStack <Object> ();
	}

	/**
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	public void setDocumentLocator(Locator locator) 
	{
		this.locator = locator;
	}

	/**
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	public void startDocument() throws SAXException 
	{
		this.finished = false;
	}

	/**
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	public void endDocument() throws SAXException 
	{
		this.finished = true;

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	public void endPrefixMapping(String prefix) throws SAXException 
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName,
	Attributes atts) throws SAXException 
	{
		isReadyForText = false;
		
		if(qName.equals("Configurations"))
		{
			
			MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> configMap = 
				new MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> (2,2);
			
			stack.push(configMap);

		}
		else if(qName.equals("Configuration"))
		{
			try
			{
				currentConfigName = atts.getValue("name");
				
				if(currentConfigName == null)
				{
					throw new SAXException("Configuration is missing a name");
				}
			}
			catch(Exception e)
			{
				getLogger().logException(e, this);
			}
		}
		else if(qName.equals("PrimitiveValue"))
		{
			String type = atts.getValue("type");
			String name = atts.getValue("name");
			String value = atts.getValue("value");
			
			
			PrimitiveValue<? extends Object> primitiveValue = 
					PrimitiveValueHelper.createPrimitiveValue(name, value, type);
			
			stack.push(primitiveValue);
		}
		else if(qName.equals("String"))
		{
			isReadyForText = true;
			String name = atts.getValue("name");
			StringValue value = new StringValue(name);
			stack.push(value);
		}
		else if(qName.equals("Unit"))
		{
			String name = atts.getValue("name");
			
			Unit unit = new Unit(name);
			stack.push(unit);
		}
		else if(qName.equals("List"))
		{
			String name = atts.getValue("name");
			String type = atts.getValue("type");
			
			ListValue list = new ListValue(name);
			list.setTypeString(type);
			stack.push(list);
		}
		else if(qName.equals("Map"))
		{
			String name = atts.getValue("name");
			String type = atts.getValue("type");
			
			MappedValue map = new MappedValue(name);
			map.setTypeString(type);
			stack.push(map);
		}
		else if(qName.equals("Value"))
		{
			isReadyForText = true;
			Value value = new Value();
			
			Object obj = stack.peek();
			
			if(obj instanceof TypedValue)
			{
				TypedValue multi = (TypedValue)obj;
				String type = multi.getTypeString();
				value.setStringType(type);
			}
			
			stack.push(value);
		}
		else if(qName.equals("UnitValue"))
		{
			String name = atts.getValue("name");
			UnitValue unitValue = new UnitValue();
			Unit unit = new Unit(name);
			
			stack.push(unit);
		}
		else if(qName.equals("NamedValue"))
		{
			isReadyForText = true;
			String name = atts.getValue("name");
			NamedValue value = new NamedValue(name);
			
			Object obj = stack.peek();
			
			if(obj instanceof TypedValue)
			{
				TypedValue multi = (TypedValue)obj;
				String type = multi.getTypeString();
				value.setStringType(type);
			}
			
			stack.push(value);
		}
		
	}

	/**
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName)
	throws SAXException 
	{
		isReadyForText = false;
		
		if(qName.equals("Configurations"))
		{
			MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> configuration =
					(MappedMaps <String, String, AbstractConfigurationValue<? extends Object>>)stack.pop();
				
				this.configMap = configuration;
		}
		else if(qName.equals("Configuration"))
		{
			
		}
		else if(qName.equals("PrimitiveValue"))
		{
			PrimitiveValue<? extends Object> value = 
					(PrimitiveValue<? extends Object>)stack.pop();
			
			Object obj = stack.peek();
			
			if(obj instanceof Unit)
			{
				((Unit)obj).addChild(value);
			}
			else if(obj instanceof MappedMaps)
			{
				MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> config =
					(MappedMaps <String, String, AbstractConfigurationValue<? extends Object>>)obj;
				
				config.putInner(currentConfigName, value.getName(), value);
			}
			else
			{
				throw new SAXException("PrimitiveValues can only be added to Units and root configuration.");
			}
		}
		else if(qName.equals("String"))
		{
			StringValue value = (StringValue)stack.pop();
			
			Object obj = stack.peek();
			if(obj instanceof Unit)
			{
				Unit rootunit = (Unit)obj;
				rootunit.addChild(value);
			}
			else if(obj instanceof MappedMaps)
			{
				MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> config =
					(MappedMaps <String, String, AbstractConfigurationValue<? extends Object>>)obj;
				
				config.putInner(currentConfigName, value.getName(), value);
			}
			else
			{
				throw new SAXException("String can only be added to Unit and root configuration.");
			}
		}
		else if(qName.equals("Unit"))
		{
			Unit unit = (Unit)stack.pop();
			
			Object obj = stack.peek();
			if(obj instanceof Unit)
			{
				Unit rootunit = (Unit)obj;
				rootunit.addChild(unit);
			}
			else if(obj instanceof MappedMaps)
			{
				MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> config =
					(MappedMaps <String, String, AbstractConfigurationValue<? extends Object>>)obj;
				
				config.putInner(currentConfigName, unit.getName(), unit);
			}
			else
			{
				throw new SAXException("Unit can only be added to Unit and root configuration.");
			}
			
		}
		else if(qName.equals("List"))
		{
			ListValue list = 
				(ListValue)stack.pop();
			
			Object obj = stack.peek();
			if(obj instanceof Unit)
			{
				Unit rootunit = (Unit)obj;
				rootunit.addChild(list);
			}
			else if(obj instanceof MappedMaps)
			{
				MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> config =
					(MappedMaps <String, String, AbstractConfigurationValue<? extends Object>>)obj;
				
				config.putInner(currentConfigName, list.getName(), list);
			}
			else
			{
				throw new SAXException("Unit can only be added to Units and root configuration.");
			}
		}
		else if(qName.equals("Map"))
		{
			MappedValue map = (MappedValue)stack.pop();
				
				Object obj = stack.peek();
				if(obj instanceof Unit)
				{
					Unit rootunit = (Unit)obj;
					rootunit.addChild(map);
				}
				else if(obj instanceof MappedMaps)
				{
					MappedMaps <String, String, AbstractConfigurationValue<? extends Object>> config =
						(MappedMaps <String, String, AbstractConfigurationValue<? extends Object>>)obj;
					
					config.putInner(currentConfigName, map.getName(), map);
				}
				else
				{
					throw new SAXException("Unit can only be added to Units and root configuration.");
				}
		}
		else if(qName.equals("Value"))
		{
			Value value = (Value)stack.pop();
			
			Object obj = stack.peek();
			if(obj instanceof ListValue)
			{
				ListValue list = (ListValue)obj;
				list.addValue(value);
			}
			else
			{
				throw new SAXException("Value can only be added to ListValue.");
			}
		}
		else if(qName.equals("NamedValue"))
		{
			NamedValue value = (NamedValue)stack.pop();
			
			Object obj = stack.peek();
			if(obj instanceof MappedValue)
			{
				MappedValue map = (MappedValue)obj;
				map.addValue(value);
			}
			else
			{
				throw new SAXException("NamedValue can only be added to MappedValue.");
			}
		}

	}

	/**
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length)
	throws SAXException 
	{
		if(this.isReadyForText){
			String text = String.copyValueOf(ch, start, length);
			
			Object obj = stack.peek();
			if(obj instanceof Value)
			{
				Value value = ((Value)obj);
				String type = value.getStringType();
				value.setValue(PrimitiveValueHelper.createPrimitive(text, type));
			}
			else if(obj instanceof StringValue)
			{
				StringValue value = ((StringValue)obj);
				value.setValue(text);
			}
			else
			{
				throw new SAXException("Only String, Value, and NamedValue have inner characters.");
			}
		}

	}
	
	protected String resolveAttrib( String uri, String localName, 
	Attributes attribs, String defaultValue ) 
	{
		String tmp = attribs.getValue( localName );
		return (tmp!=null)?(tmp):(defaultValue);
	}

	/**
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	public void ignorableWhitespace(char[] ch, int start, int length)
	throws SAXException 
	{
		// do nothing

	}

	/**
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	public void processingInstruction(String target, String data)
	throws SAXException 
	{
		// do nothing

	}

	/**
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	public void skippedEntity(String name) throws SAXException 
	{
		// do nothing

	}

	/**
	 * @see org.xml.sax.DTDHandler#notationDecl(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void notationDecl(String name, String publicId, String systemId)
	throws SAXException 
	{
		// do nothing

	}

	/**
	 * @see org.xml.sax.DTDHandler#unparsedEntityDecl(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void unparsedEntityDecl(String name, String publicId,
	String systemId, String notationName) throws SAXException 
	{
		// do nothing

	}

	/**
	 * @see org.xml.sax.ext.EntityResolver2#getExternalSubset(java.lang.String, java.lang.String)
	 */
	public InputSource getExternalSubset(String name, String baseURI)
	throws SAXException, IOException 
	{
		// do nothing
		return null;
	}

	/**
	 * @see org.xml.sax.ext.EntityResolver2#resolveEntity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public InputSource resolveEntity(String name, String publicId,
	String baseURI, String systemId) throws SAXException, IOException 
	{
		// do nothing
		return null;
	}

	/**
	 * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
	 */
	public InputSource resolveEntity(String publicId, String systemId)
	throws SAXException, IOException 
	{
		// do nothing
		return null;
	}

    /**
     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
     */
    public void error(SAXParseException e) throws SAXException
    {
        getLogger().logException(e, this);
    }

    /**
     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
     */
    public void fatalError(SAXParseException e) throws SAXException
    {
        getLogger().logError(e, e.getLocalizedMessage() , this);
    }

    /**
     * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
     */
    public void warning(SAXParseException e) throws SAXException
    {
    	getLogger().logWarn(e.getMessage(), this);
    }
    

	public LoggerSession getLogger() 
	{
		if(logger == null)
		{
			logger = LoggerSession.instance();
			logger.setConfigLocation(new File("./config/logger.xml"));
		}
		
		return logger;
	}

	public void setLogger(LoggerSession logger) 
	{
		this.logger = logger;
	}

	public boolean isFinished()
	{
		return finished;
	}
	
	public MappedMaps<String, String, AbstractConfigurationValue<? extends Object>> 
	getConfiguration()
	{
		return this.configMap;
	}

}
