
package com.bramble;



import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class BrambleLanuch {
    static void testOption(String[] args) {
        String dir=System.getProperty("user.dir")+"/";
        Option samper = new Option("s", "samper");

        Option help = new Option("help", "this usage message");
        Option version = new Option("version", "print the version information and exit");
        Option pid = new Option("p", true, "Trace on existing jvm PID");
        Option F = new Option("F", true, "sampler times per second");
        Option output = new Option("o", true, "output filepath");
        Option duration = new Option("t", true, "duration (e.g., 2h 10m 100s)");
        //Option agent = new Option("f", true, "attach agent jar filepath");
        Option methodList = new Option("m", true, "the metod list");
        Option deep = new Option("d", true, "Max stack trace depth");
        //Option interverl = new Option("i", true, "Sampling interval (e.g., 20ms 100us) ");
        Options options = new Options();
        options.addOption(samper);
        options.addOption(help);
        options.addOption(version);
        options.addOption(pid);
        options.addOption(F);
        options.addOption(duration);
        options.addOption(output);
        //options.addOption(agent);
        options.addOption(methodList);
        options.addOption(deep);
        //options.addOption(interverl);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
                System.exit(1);
            }
            if(!line.hasOption("p")) {
                System.err
                        .println("You must specify '-p' option");
                System.exit(1);}
            if(!line.hasOption("t")) {
                System.err
                        .println("You must specify `-t' option.");
                System.exit(1);}

            String agentjarpath = dir+"JVM.jar";
            String intervel = line.getOptionValue("F");
            String deepMax = "8000";
            if(line.hasOption("d")) {
                deepMax = line.getOptionValue("d");
            }

            long time =0;
            String outputpath  =line.getOptionValue("o")+"/";
            String duration1 =line.getOptionValue("t");
            time = Long.valueOf(duration1).longValue();

            if(time>84600) {
                System.err
                        .println("Max duration：86400");
                System.exit(1);}
            StringBuffer sBuffer = new StringBuffer(intervel);
            sBuffer.append("@");
            sBuffer.append(deepMax);
            sBuffer.append("@");
            sBuffer.append(dir);
            sBuffer.append(outputpath);
            sBuffer.append("@");
            sBuffer.append(duration1);
            sBuffer.append("s");

            sBuffer.append("@");

            VirtualMachine vm = null;
            try {
                vm = VirtualMachine.attach(line.getOptionValue("p"));
                vm.loadAgent(agentjarpath, sBuffer.toString());
                vm.detach();
            } catch (AttachNotSupportedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AgentLoadException e) {
                e.printStackTrace();
            } catch (AgentInitializationException e) {
                e.printStackTrace();
            }





            if (line.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            }
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }

    }

    public static void main(String[] args) {
        testOption(args);

    }
}
