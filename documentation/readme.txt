The following is some general documentation on the usage of the "us-common" project:

There are eight packages in the us-common project.

	1) com.wn.retail.event
		This is where I put event like
		A) file change events
		B) data change events
	
	2) com.wn.retail.exception
		this is where all exceptions are kept
	
	3) com.wn.retail.filemonitor
		These are the classes used to monitor file and folder changes.  This will fire a file or folder change on ADD, UPDATE, or DELETE
		A) The FileMonitorSession is the runnable class with a main.
		B) The FileMonitor is created by the FileMonitorConfig.xml in the config directory
		
	4) com.wn.retail.logging
		This is where I created helper classes for custom logging.  There is a logger.xml where this is configured.
		A) Logger is an interface that all logging classes will implement.
		B) The LoggerSession is a Singleton design for starting logs and loading the config (logger.xml)
		C) LoggerContainer is a container object that can hold multiple Logger implementations.
		D) LoggerLevel is an enumeration object that indicates the level of the logging event: DEBUG, INFO, WARNING, ERROR, or FATAL.
		E) FileLogger is an implementation of the Logger interface for logging to a file.
		F) ConsoleLogger is an implementation of the Logger interface for logging to a any class implementing the PrintStream interface.
		
	5) com.wn.retail.model
		This package is used for data modeling and monitoring data change events.
		A) there is a generic DataModel class that can monitor changes to data.  It will fire a DataStateChangeEvent indicating an ADD, UPDATE, or DELETE
		B) TableDataModel is a data structure for creating grid or table data models.  This class extends DataModel.
		C) RowDataModel is a data structure that represents a row in a table or grid data structure.
		D) HeaderDataModel represents the column headers in a table or grid data structure.
		
	6) com.wn.retail.utilities
		This package is use to put utility classes that will help make tasks simplified
		A) CSVParser is a simple class for parsing csv files.
		B) CSVParser2 is a full featured class for parsing csv files.
		C) RowSet is the object represents a row in a csv file.
		D) There are many of the common collection structures that have a different implementation than the java.util package.
		
	7) com.wn.retail.utilities.comparitor
		These are classes that implement the Comparable interface for sorting collections.
		
	8) com.wn.retail.utilities.zip
		These are classes used to zip and unzip files.
		
	
	
---------->FileMonitorConfig.xml<------------

<?xml version="1.0" encoding="UTF-8"?>
<config>
	<!-- Multiple configurations can be added for Folders, Files, Polling Interval, and Listeners -->
	
	<!-- Every FileMonitorConfig element must contain a name attribute -->
	<FileMonitorConfig name="monitor1">
	
		<!-- This configures the monitors pollinginterval in milliseconds -->
		<Timer>10000</Timer>		
		
		<!-- Multiple files can be monitored in this secion-->
		<Files>
			<File>C:\\DevTools\\workspace\\tpiscan\\trunk-us\\customers\\us-common\\config\\03300817.scl</File>
		</Files>
		
		<!-- Multiple listeners can be configured for listening for this monitor -->
		<Listeners>
			<!-- These are classes that implement the FileChangeListener interface -->
			<Listener>com.wn.retail.filemonitor.Type2Listener</Listener>
		</Listeners>
		
	</FileMonitorConfig>
	
	<FileMonitorConfig name="monitor2">
		
		<Timer>5000</Timer>
		
		<Folders>
		
			<!-- Multiple folders can be monitored for each FileMonitorConfig -->
			<!-- Both Files and Folders can exist in each FileMonitorConfig -->
			<Folder>
				<!-- This is the folder location -->
				<Location recursive="true">C:\\DevTools\\workspace\\tpiscan\\trunk-us\\customers\\us-common\\</Location>
				
				<!-- Multiple patters can be used to include or exclude a file pattern -->
				<Patterns>
					<Pattern name="xml_files" include="true"> 
						<Description>include any xml file</Description>
						<File>[a-zA-Z0-9]+.xml</File>
					</Pattern>
					<Pattern name="java_files" include="true">
						<Description>include any java file</Description>
						<File>[a-zA-Z0-9]+.java</File>
					</Pattern>
					<Pattern name="class_files" include="false">
						<Description>include any class file</Description>
						<File>[a-zA-Z0-9]+.class</File>
					</Pattern>
				</Patterns>
			</Folder>
		</Folders>
		
		<Listeners>
			<Listener>com.wn.retail.filemonitor.Type2Listener</Listener>
		</Listeners>
		
	</FileMonitorConfig>
</config>
		
	
	
---------->logger.xml<------------


<?xml version="1.0" encoding="UTF-8"?>
<LoggerConfig>

	<!-- Each logger can contain an optional classPath and/or logLevel attribute  -->
	<!-- The classPath tellsthe logger what classes this logger will log for -->
	<!-- The logLevel will only log if log event is less that the highest log level: -->
	<!-- DEBUG, INFO, WARNING, ERROR, FATAL (from smallest to highest) -->
	<Logger classPath="com.wn.*" logLevel="INFO">
	
		<!-- This is a file logger  -->
		<File location="./log/filemonitor.log"/>
		
		<!-- This will log to any class that implements the PrintStream interface  -->
		<Console class="System.out"/>
		
	</Logger>
</LoggerConfig>