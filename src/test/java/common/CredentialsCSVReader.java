package common;


import testdataobjects.EmployeeProfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CredentialsCSVReader {

    public static HashMap<String, EmployeeProfile> readAndLoadCSVData(){

        String csvFile = "src/test/resources/data/employee_credentials.csv";
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String, EmployeeProfile> returnList = new  HashMap<String, EmployeeProfile>();
        EmployeeProfile employeeProfile;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                employeeProfile = new EmployeeProfile();

                // use comma as separator
                String[] record = line.split(cvsSplitBy);
                employeeProfile.setEmpno(record[0]);
                employeeProfile.setUsername(record[1]);
                //employeeProfile.setPassword(record[2]);
                employeeProfile.setLastName(record[3]);
                employeeProfile.setFirstName(record[4]);
                employeeProfile.setPosition(record[5]);
                returnList.put(record[5], employeeProfile);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("End reading csv file: " + returnList.size());

        return returnList;

    }

    /*public static <c> HashMap<String, Object> readCSV(String fileName, Class<?> c){
        HashMap<String, Object> ret = new  HashMap<String, Object>();
        BufferedReader br = null;

       try(
               Reader reader = Files.newBufferedReader(Paths.get(fileName));
        ) {
           CsvToBean<c> csvToBean = new CsvToBeanBuilder(reader)
                   .withType(c)
                   .withIgnoreLeadingWhiteSpace(true)
                   .build();

           Iterator<c> csvUserIterator = ((CsvToBean) csvToBean).iterator();

           while (((Iterator) csvUserIterator).hasNext()) {
               CSVUser csvUser = csvUserIterator.next();
               System.out.println("Name : " + csvUser.getName());
               System.out.println("Email : " + csvUser.getEmail());
               System.out.println("PhoneNo : " + csvUser.getPhoneNo());
               System.out.println("Country : " + csvUser.getCountry());
               System.out.println("==========================");
           }
       }catch (Exception e) {
            System.out.println(e);
        }finally {
           if(br!=null){
               try {
                   br.close();
               } catch (IOException e) {
                   System.out.println(e.getMessage());
               }
           }

        }
        return ret;
    }*/




}
