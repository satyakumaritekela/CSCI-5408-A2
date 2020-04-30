import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NavaScotiaParks {
	public static void main(String args[]) throws IOException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		/** extracting all the parks of Canada **/
		FileReader fr = new FileReader("DNR_Camping_Parks_Reservation_Data_2016.csv");
		br = new BufferedReader(fr);
		
		/** writing into new file **/
		FileWriter fw = new FileWriter("file1.csv");
		bw =new BufferedWriter(fw);
		
		String fileReader;
		int flag = 0, firstRow = 0;
		String rowData[] = null;
		
		while((fileReader = br.readLine()) != null) {
			rowData = fileReader.split(",");
			for(int i = 0; i < rowData.length; i++) {
				/** checking if the park locates in canada or not **/
				if(firstRow > 0 && !rowData[2].trim().equals("CANADA")) {
					continue;
				}
				else {
					/** 13 columns so checking with modulus 13 to get into each row **/
					if(i % 13 == 0) {
						if(flag > 0) {
							bw.newLine();
						}
					}
					/** writing row data into new file **/
					bw.write(rowData[i]+",");
					/** incrementing the flag to scan for next row **/
					flag++;
				}
				
			}
			firstRow++;
		}
		bw.close();
		br.close();
		
		/** extracting only certain columns in file2.csv **/
		FileReader fr1 = new FileReader("file1.csv");
		br = new BufferedReader(fr1);

		/** writing into new file **/
		FileWriter fw1 = new FileWriter("file2.csv");
		bw = new BufferedWriter(fw1);
		
		/** re-intializing to null **/
		fileReader = null;
		flag = 0;
		while((fileReader = br.readLine()) != null) {
			rowData = fileReader.split(",");
			for(int i = 0; i < rowData.length; i++) {
				/** 13 columns so checking with modulus 13 to get into each row **/
				if(i % 13 == 0) {
					if(flag > 0) {
						bw.newLine();
					}
				}
				/** checking for the data only parkname, State, ... **/
				if(i == 0 || i == 1 || i == 5 || i == 6 || i == 7 || i == 8) {
					/** writing row data into new file **/
					bw.write(rowData[i]+",");
				}
				/** incrementing the flag to scan for next row **/
				flag++;
			}
		}
		br.close();
		bw.close();
		
		/** Scanning Equipment and replacing with other content in file3.csv **/
		FileReader fr2 = new FileReader("file2.csv");
		br = new BufferedReader(fr2);

		/** writing into new file **/
		FileWriter fw2 = new FileWriter("file3.csv");
		bw = new BufferedWriter(fw2);
		
		/** re-intializing to null **/
		rowData = null;
		flag = 0;
		while((fileReader = br.readLine()) != null) {
			rowData = fileReader.split(",");
			for(int i = 0; i < rowData.length; i++) {
				/** 6 columns so checking with modulus 6 to get into each row **/
				if(i % 6 == 0) {
					if(flag > 0) {
						bw.newLine();
					}
				}
				/** checking with the equipment and changing less than to LT **/
				if(i == 5 && rowData[5].matches("Less than(.*)")) {
					bw.write(rowData[5].replaceAll("Less than ", "LT")+",");
				}
				/** checking with the equipment and changing single tent to ST **/
				else if(i == 5 && rowData[5].matches("Single Tent(.*)")) {
					bw.write(rowData[5].replaceAll("Single Tent", "ST")+",");
				}
				else {
					/** writing row data into new file **/
					bw.write(rowData[i]+",");
				}
				/** incrementing the flag to scan for next row **/
				flag++;
			}
		}
		br.close();
		bw.close();
		
		/** extracting all the parks of NavoScotia **/
		FileReader fr3 = new FileReader("file3.csv");
		br = new BufferedReader(fr3);
		
		/** writing into new file **/
		FileWriter fw3 = new FileWriter("file4.csv");
		bw =new BufferedWriter(fw3);

		/** re-intializing to null **/
		rowData = null;
		flag = 0; 
		firstRow = 0;
		while((fileReader = br.readLine()) != null) {
			rowData = fileReader.split(",");
			for(int i = 0; i < rowData.length; i++) {
				/** checking if the park locates in NavoScotia or not **/
				if(firstRow > 0 && !rowData[1].trim().equals("NS")) {
					continue;
				}
				else {
					/** 6 columns so checking with modulus 6 to get into each row **/
					if(i % 6 == 0) {
						if(flag > 0) {
							bw.newLine();
						}
					}
					/** writing row data into new file **/
					bw.write(rowData[i]+",");
					/** incrementing the flag to scan for next row **/
					flag++;
				}
				
			}
			firstRow++;
		}
		bw.close();
		br.close();
	}
}
