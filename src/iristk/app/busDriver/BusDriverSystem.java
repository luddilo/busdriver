/*******************************************************************************
 * Copyright (c) 2014 Gabriel Skantze.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Gabriel Skantze - initial API and implementation
 ******************************************************************************/
package iristk.app.busDriver;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import iristk.speech.SpeechGrammarContext;
import iristk.speech.Voice.Gender;
import iristk.speech.windows.WindowsRecognizerFactory;
import iristk.speech.windows.WindowsSynthesizer;
import iristk.system.IrisUtils;
import iristk.system.SimpleDialogSystem;
import iristk.util.Language;
import iristk.cfg.SRGSGrammar;
import iristk.flow.FlowModule;

public class BusDriverSystem {
	
	// Stops, only used for answering what the next stop is
	public String[] stops = {"Mumindalen", "Stockholm", "New York"};
	
	public BusDriverSystem() throws Exception {
		// Create the system
		SimpleDialogSystem system = new SimpleDialogSystem(this.getClass());
		
		// Set the language of the system
		system.setLanguage(Language.ENGLISH_US);
		
		// Uncomment this if you want to turn on logging
		String logPath = "C:/Users/ludvig/IrisTK/iristk_logging";
		system.setupLogging(new File(logPath), true);
		
		// Set up the GUI
		system.setupGUI();
		
		// Add the recognizer to the system
		system.setupRecognizer(new WindowsRecognizerFactory());
		
		// Add a synthesizer to the system		
		system.setupSynthesizer(new WindowsSynthesizer(), Gender.FEMALE);
			
		// Dynamic nextStop generator since we want different answers every time. Because that's cool. 
		final String[] localStops = this.stops;  
	    Callable<String> nextStop = new Callable<String>(){
	    	public String call(){
	    		int index = new Random().nextInt(localStops.length);
	    		return stops[index];
	        }
	    };
		
		// Add the flow
		system.addModule(new FlowModule(new BusDriverFlow(
				nextStop
		)));
		
		// Load a grammar in the recognizer
		SpeechGrammarContext grammar = new SpeechGrammarContext(new SRGSGrammar(system.getPackageFile("BusDriverGrammar.xml")));
		// Tried to extract stuff here for getNextStop but meh... not easy?!
		system.loadContext("default", grammar);

		// Start the interaction
		system.sendStartSignal();
	}

	public static void main(String[] args) throws Exception {
		new BusDriverSystem();
	}

}
