package com.avacado.StepDef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {
 public static void main(String[] args) {
  Process p;
  try {
   //String[] cmd = { "sh", "/home/gslab/Startup.sh"};
   //p = Runtime.getRuntime().exec(cmd); 
	  String homeDir = System.getenv("HOME");
	  String[] cmd = { homeDir + "/Startup.sh", "foo", "bar" };
	  Process p1 = Runtime.getRuntime().exec(cmd);
   p1.waitFor(); 
   BufferedReader reader=new BufferedReader(new InputStreamReader(
    p1.getInputStream())); 
   String line; 
   while((line = reader.readLine()) != null) { 
    System.out.println(line);
   } 
  } catch (IOException e) {
   e.printStackTrace();
  } catch (InterruptedException e) {
   e.printStackTrace();
  }
 }
}