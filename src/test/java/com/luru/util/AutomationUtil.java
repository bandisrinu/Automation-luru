package com.luru.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AutomationUtil {

	protected static final Logger logger = Logger.getLogger(AutomationUtil.class.getName());

	public static void main(String[] args) {
		// TODO Auto-gene
		String TEST_DATA_PROPERTIES = "testdata_en.properties";
		String EXPECTED_ASS_PROPERTIES = "expectedassertions_en.properties";
		String LANG_ASS_PROPERTIES = "xpaths_en.properties";
		System.out.println("French " + getLanguageFilePath(TEST_DATA_PROPERTIES, "fr"));
		System.out.println("French " + getLanguageFilePath(EXPECTED_ASS_PROPERTIES, "fr"));

		TEST_DATA_PROPERTIES = "testdata.properties";
		EXPECTED_ASS_PROPERTIES = "expectedassertions.properties";
		System.out.println("Default " + getLanguageFilePath(TEST_DATA_PROPERTIES, "en"));
		System.out.println("French " + getLanguageFilePath(EXPECTED_ASS_PROPERTIES, "en"));

		String langxPathFile = getLanguageFilePath(LANG_ASS_PROPERTIES, "en");
		FileReader langxPathReader;
		try {
			langxPathReader = new FileReader(langxPathFile);
			Properties langXPathsProp = new Properties();
			langXPathsProp.load(langxPathReader);

			System.out.println("Property value " + langXPathsProp.getProperty("loginpage.btn.proceed.secure.xpath"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getLanguageFilePath(String fileName, String lang) {

		logger.info("Starting of getLanguageFilePath method");
		if (lang == null) {
			lang = "en";
		}

		if (fileName.contains("_")) {
			fileName = fileName.split("_")[0] + "_" + lang + ".properties";
		}

		logger.info("End of getLanguageFilePath method");
		return Constants.LANG_RESOURCES_FILE_PATH + "/" + lang + "/" + fileName;
	}
}
