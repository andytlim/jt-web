package com.jt.web.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiaa.bi.oraclepr.model.CommandStatus;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ShellService {

	protected static Logger log = LoggerFactory.getLogger(ShellService.class);
	
	public static CommandStatus executeCommand(String user, String password, String host, String command) {

		CommandStatus cmdStatus = new CommandStatus();
		
		try {
			// Connect to remote host
			JSch jsch = new JSch();
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			config.put("PreferredAuthentications", "password");
			Session session = jsch.getSession(user,
					host, 22);
			session.setConfig(config);
			session.setPassword(password);
			session.connect();
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			InputStream in = channelExec.getInputStream();
			
			// Set command
			command += "\necho exit code: $?";
			log.info(command);
			channelExec.setCommand(command);
			channelExec.connect();
			
			// Read console
			String line = "";
			boolean success = false;
			StringBuilder output = new StringBuilder();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			while ((line = reader.readLine()) != null) {
				success = (line.equals("exit code: 0")) ? true : success;
				 line = line.trim(); // remove leading and trailing whitespace
				    if (!line.equals("")) // don't write out blank lines
				    {
				        output.append(line).append("\n");
				    }
			}

			session.disconnect();
			
			if (success) {
				log.info(output.toString());
				cmdStatus.setStatus(200);
				cmdStatus.setOutput(output.toString());
				return cmdStatus;
			} else {
				log.info("The following command failed:\n" + command);
				cmdStatus.setStatus(500);
				cmdStatus.setOutput("The following command failed:\n" + command);
				return cmdStatus;
			}
		} catch (Exception e) {
			log.error("An issue occured while connecting to the remote host", e);
			cmdStatus.setStatus(500);
			cmdStatus.setOutput("An issue occured while connecting to the remote host.");
			return cmdStatus;
		}
	}
}
