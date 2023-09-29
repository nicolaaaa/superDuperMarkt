package com.superdupermarkt;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.opencsv.CSVReader;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ImportCSV 
{

    static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Product> getProductsFromFile( String Filepath ) throws Exception
    {
        List<Product> products = new ArrayList<>();
        InputStream inputStream;
        ClassLoader classLoader = ImportCSV.class.getClassLoader();

        inputStream = classLoader.getResourceAsStream(Filepath);
        if (inputStream != null) {
            // Create a CSV parser
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            // Read the first line (header) without processing it
            String[] header = csvReader.readNext();

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length >= 5) {
                    if (nextLine[0].equals("Kaese")){
                        Käse product = new Käse(nextLine[1], Integer.parseInt(nextLine[2]), LocalDate.parse(nextLine[3], DATE_FORMAT), Double.parseDouble(nextLine[4]));
                        products.add(product);

                    }else if(nextLine[0].equals("Wein")){
                        Wein product = new Wein(nextLine[1], Integer.parseInt(nextLine[2]), LocalDate.parse(nextLine[3], DATE_FORMAT), Double.parseDouble(nextLine[4]));
                        products.add(product);

                    }else if(nextLine[0].equals("Product")){
                    Product product = new Product(nextLine[1], Integer.parseInt(nextLine[2]), LocalDate.parse(nextLine[3], DATE_FORMAT), Double.parseDouble(nextLine[4]));                    
                    products.add(product);

                    }else{
                        throw new Exception("Unrecognized Item: "+nextLine[0]);
                    }
                }
            }
        }else{
            throw new Exception("File not found. Check if Filepath is correct");
        } 
        return products;   
    }

}
