import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJson {
    static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) throws Exception
    {

        byte[] jsonData;

        {
            try {
                jsonData = Files.readAllBytes(Paths.get("/home/gslab/eclipse-workspace/Avacado/resources/machine.json"));
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonData);
                JsonNode hosts=rootNode.path("machines");
                Iterator<JsonNode> elements=hosts.elements();
                ArrayList<HostData> HostList= new ArrayList<>();

                while(elements.hasNext()){
                HostData hd=new HostData();
                    JsonNode host = elements.next();
                    JsonNode rootNode1 = objectMapper.readTree(host.toString());
                    hd.setIp(rootNode1.get("host").toString());
                    hd.setUsername(rootNode1.get("user").toString());
                    hd.setPassword(rootNode1.get("password").toString());
                    HostList.add(hd);
                    /*System.out.println(rootNode1.get("host").toString());
                    System.out.println(rootNode1.get("user").toString());
                    System.out.println(rootNode1.get("password").toString());*/
                }
                for (int counter = 0; counter < HostList.size(); counter++) {
                    HostData hd=HostList.get(counter);
                    System.out.println(hd.getIp());
                    System.out.println(hd.getUsername());
                    System.out.println(hd.getPassword());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}