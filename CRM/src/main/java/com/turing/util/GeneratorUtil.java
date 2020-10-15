package com.turing.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 *ÄæÏò¹¤³Ì
 * @author thomas
 *
 */
public class GeneratorUtil {
	@Test
	public void test1(){

			  try {
				List<String> warnings = new ArrayList<String>();
				   boolean overwrite = true;
				   File configFile = new File(getClass().getClassLoader().getResource("mbg.xml").getPath());
				   ConfigurationParser cp = new ConfigurationParser(warnings);
				   Configuration config = cp.parseConfiguration(configFile);
				   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
				   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
				   myBatisGenerator.generate(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	

}
