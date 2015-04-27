package jpm.commands;

import jpm.commands.util.CalcProfitIOCtrl;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class CalcProfitCommands implements CommandMarker {

	private CalcProfitIOCtrl calcProfitIoCtrl = new CalcProfitIOCtrl();

	@CliCommand(value = "calcprofit", help = "Calculates profit for the given input file, and saves the summary in the output file")
	public String calcprofit(
			@CliOption(key = { "input" }, mandatory = true, help = "The input csv data file") final String input,
			@CliOption(key = { "output" }, mandatory = false, help = "The output summary file", specifiedDefaultValue = "calcprofit.output.csv") final String output) {

		String retMessage = calcProfitIoCtrl.calcProfitFileHandling(input, output);

		return retMessage;
	}

}
