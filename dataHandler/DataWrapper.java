package dataHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import simulation.clientObjects.Creature;

public class DataWrapper {
	Properties properties;

	public DataWrapper() {
		properties = Properties.getInstance();
	}

	public void wrapData(CreaturesPerCycleHolder cycleHolder) {
		List<List<Creature>> cycle1 = cycleHolder.getCreaturesFromCycle(1);
		List<List<Creature>> cycle100 = cycleHolder.getCreaturesFromCycle(99);
		List<List<Creature>> cycle500 = cycleHolder.getCreaturesFromCycle(499);
		List<List<Creature>> cycle1250 = cycleHolder.getCreaturesFromCycle(1249);
		List<List<Creature>> cycleLast = cycleHolder.getCreaturesFromCycle(cycleHolder.get().size()-1);
		createFile(cycle1, new File("c:\\EvolutionOutput\\cycleStart.xls"));
		createFile(cycle100, new File("c:\\EvolutionOutput\\cycle100.xls"));
		createFile(cycle500, new File("c:\\EvolutionOutput\\cycle500.xls"));
		createFile(cycle1250, new File("c:\\EvolutionOutput\\cycle1250.xls"));
		createFile(cycleLast, new File("c:\\EvolutionOutput\\cycleLast.xls"));

	}

	public List<String> getAllDnaSequences(List<Creature> list) {
		List<String> uniqueDnaSequence = new ArrayList<String>();
		for (Creature creature : list) {
			String sequence = creature.getDna().getSequence();
			if (!uniqueDnaSequence.contains(sequence)) {
				uniqueDnaSequence.add(sequence);
			}
		}
		return uniqueDnaSequence;
	}

	public void createFile(List<List<Creature>> listList, File file) {
		StringReader reader = new StringReader();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet1 = workbook.createSheet("Cycle1");
			// Heading
			HSSFRow heading = sheet1.createRow(0);
			for(int j = 0; j < listList.size();j++){
				List<Creature> list = new ArrayList<Creature>(listList.get(j));
			heading.createCell(0+j*13).setCellValue("DNA");
			heading.createCell(1+j*13).setCellValue("LifeExpectancy");
			heading.createCell(2+j*13).setCellValue("Height");
			heading.createCell(3+j*13).setCellValue("Width");
			heading.createCell(4+j*13).setCellValue("Speed");
			heading.createCell(5+j*13).setCellValue("Flight");
			heading.createCell(6+j*13).setCellValue("OptTemperature");
			heading.createCell(7+j*13).setCellValue("NumOffspring");
			heading.createCell(8+j*13).setCellValue("PairChance");
			heading.createCell(9+j*13).setCellValue("Carnivorous");
			heading.createCell(10+j*13).setCellValue("#Environment");
			List<String> uniqueDnaSequence = getAllDnaSequences(list);
			for (int i = 0; i < uniqueDnaSequence.size(); i++) {
				HSSFRow row = sheet1.getRow(i+1);
				if(row == null){
					row= sheet1.createRow(i+1);
				}
				int allelLength = properties.getAllelLength();
				row.createCell(0+j*13).setCellValue(uniqueDnaSequence.get(i));
				row.createCell(1+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 1, allelLength));
				row.createCell(2+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 2, allelLength));
				row.createCell(3+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 3, allelLength));
				row.createCell(4+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 4, allelLength));
				row.createCell(5+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 5, allelLength));
				row.createCell(6+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 6, allelLength));
				row.createCell(7+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 7, allelLength));
				row.createCell(8+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 8, allelLength));
				row.createCell(9+j*13).setCellValue(reader.readHexStringToInt(uniqueDnaSequence.get(i), 9, allelLength));
				row.createCell(10+j*13).setCellValue(j);

			}
			sheet1.autoSizeColumn(0+j*11);
			}
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			workbook.close();
			System.out.println("Files outputted to c:/EvolutionOutputs");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
