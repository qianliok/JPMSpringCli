package jpm.commands.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import jpm.commands.databeans.TickerPriceData;
import jpm.commands.databeans.TickerProfitData;
import jpm.commands.exception.InvalidLineDataFormatException;
import jpm.commands.util.ProfitFormulaCtrl.TickerType;

/**
 * Calculate Profit IO Controller class
 * 
 * @author Qian Li
 *
 */
public class CalcProfitIOCtrl {
	private long largeFileSize;
	private final static String outputHeader = "instrument_type,name,quantity,profit";
	public static Charset ENCODING = StandardCharsets.UTF_8;

	public CalcProfitIOCtrl() {
		 largeFileSize = 100000000;
	}

	public CalcProfitIOCtrl(long largeFileSize) {
		this.largeFileSize = largeFileSize;
	}

	/**
	 * This method will use different approach to process the profit calculation
	 * depends on the file size.
	 * 
	 * @param input
	 *            the input file name
	 * @param output
	 *            the output file name
	 * @return the system message
	 */
	public String calcProfitFileHandling(String input, String output) {
		long filesize = 0;
		String retMessage = "";
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(new File(input), "r")) {
			filesize = randomAccessFile.length();

			if (filesize < largeFileSize) {
				retMessage = calcSmallFile(input, output);
			} else {
				retMessage = calcLargeFile(input, output);
			}

		} catch (IOException e) {
			e.printStackTrace();
			retMessage = "Error: Input file [" + input + "] not found";
		}
		return retMessage;
	}

	/**
	 * Calculate large input file, read and write the large line by line
	 * minimize the number of IO
	 * 
	 * @param input
	 *            the input file name
	 * @param output
	 *            the output file name
	 * @return the system message
	 * @throws IOException
	 */
	public String calcLargeFile(String input, String output) throws IOException {
		LinkedHashMap<String, TickerProfitData> outputDataMap = new LinkedHashMap<String, TickerProfitData>();
		/* Read the file line by line */
		Path path = Paths.get(input);
		try (Scanner sc = new Scanner(path, ENCODING.toString())) {
			// header line
			String line = sc.nextLine();

			while (sc.hasNextLine()) {
				line = sc.nextLine();
				try {
					TickerProfitData tpData = processLine(line);

					if (tpData != null) {
						if (outputDataMap.containsKey(tpData.getName())) {
							TickerProfitData exData = outputDataMap.get(tpData.getName());
							exData.setQuantity(exData.getQuantity() + tpData.getQuantity());
							exData.setProfit(exData.getProfit() + tpData.getProfit());
						} else {
							outputDataMap.put(tpData.getName(), tpData);
						}
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				} catch (InvalidLineDataFormatException ile) {
					ile.printStackTrace();
				} catch (IllegalArgumentException iae) {
					iae.printStackTrace();
				}
			}
		}

		/* Write out the data line by line */
		Path outputPath = Paths.get(output);
		try (BufferedWriter writer = Files.newBufferedWriter(outputPath, ENCODING)) {
			writer.write(outputHeader);
			writer.newLine();
			for (TickerProfitData data : outputDataMap.values()) {
				writer.write(data.toString());
				writer.newLine();
			}
		}

		return "Large File Calulcation used. Output file[" + output + "] has written successfully";
	}

	/**
	 * Calculate small input file, read and write entire file at once to
	 * minimize the number of IO
	 * 
	 * @param input
	 *            the input file name
	 * @param output
	 *            the output file name
	 * @return the system message
	 * @throws IOException
	 */
	public String calcSmallFile(String input, String output) throws IOException {

		/* Read all lines at once */
		Path path = Paths.get(input);
		List<String> inputData = Files.readAllLines(path, ENCODING);
		LinkedHashMap<String, TickerProfitData> outputDataMap = new LinkedHashMap<String, TickerProfitData>();

		/* The first line is the header */
		for (int i = 1; i < inputData.size(); i++) {
			try {
				TickerProfitData tpData = processLine(inputData.get(i));

				if (tpData != null) {
					if (outputDataMap.containsKey(tpData.getName())) {
						TickerProfitData exData = outputDataMap.get(tpData.getName());
						exData.setQuantity(exData.getQuantity() + tpData.getQuantity());
						exData.setProfit(exData.getProfit() + tpData.getProfit());
					} else {
						outputDataMap.put(tpData.getName(), tpData);
					}
				}
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			} catch (InvalidLineDataFormatException ile) {
				ile.printStackTrace();
			} catch (IllegalArgumentException iae) {
				iae.printStackTrace();
			}
		}

		List<String> outputData = new LinkedList<String>();
		outputData.add(outputHeader);
		for (TickerProfitData data : outputDataMap.values()) {
			outputData.add(data.toString());
		}

		/* Write out all the data at once */
		Path outputPath = Paths.get(output);
		Files.write(outputPath, outputData, ENCODING);
		return "Small File Calulcation used. Output file[" + output + "] has written successfully";
	}

	/**
	 * Process one line of data
	 * 
	 * @param aLine
	 *            the one line of data
	 * @return The TickerProfitData which only contains the essential summary
	 *         data
	 * @throws NumberFormatException
	 * @throws InvalidLineDataFormatException
	 */
	public TickerProfitData processLine(String aLine) throws IllegalArgumentException, NumberFormatException,
			InvalidLineDataFormatException {

		String[] lineBreaks = aLine.split(",");

		TickerType instrumentType = TickerType.valueOf(lineBreaks[0]);

		TickerPriceData tData = null;

		String name = lineBreaks[1];
		int quantity = Integer.parseInt(lineBreaks[2]);

		if (instrumentType == TickerType.Equity && lineBreaks.length == 5) {
			double buyPrice = Double.parseDouble(lineBreaks[3]);
			double sellPrice = Double.parseDouble(lineBreaks[4]);
			tData = new TickerPriceData.TickerPriceBuilder(instrumentType, name, quantity).buyPrice(buyPrice).sellPrice(sellPrice)
					.build();

		} else if (instrumentType == TickerType.Bond && lineBreaks.length == 6) {
			double coupon = Double.parseDouble(lineBreaks[5]);
			tData = new TickerPriceData.TickerPriceBuilder(instrumentType, name, quantity).coupon(coupon).build();

		} else {
			tData = null;
			throw new InvalidLineDataFormatException();
		}

		TickerProfitData tpData = new TickerProfitData(tData.getBaseData());
		tpData.setProfit(ProfitFormulaCtrl.calcTickerData(tData));

		return tpData;
	}
}
