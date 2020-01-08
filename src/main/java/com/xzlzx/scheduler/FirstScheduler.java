package com.xzlzx.scheduler;

import io.quarkus.scheduler.Scheduled;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FirstScheduler {

    @Scheduled(every = "10s")
    void cmd() {
        try {
            Process process = Runtime.getRuntime().exec("cmd.exe /c dir");
            int status = process.waitFor();

            System.out.println(status);
            InputStream in = process.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while(line!=null) {
                System.out.println(line);
                line = br.readLine(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
