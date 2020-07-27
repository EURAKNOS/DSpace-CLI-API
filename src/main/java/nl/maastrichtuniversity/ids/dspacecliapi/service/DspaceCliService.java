package nl.maastrichtuniversity.ids.dspacecliapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class DspaceCliService {
    public DspaceCliService() {
    }

    @Async
    public void registerObjects(String collectionId, String source, String mapFile) throws IOException, InterruptedException {
        log.info("Registering objects into DSpace for collection-id: {}, source: {}, mapfile: {}.",
                collectionId, source, mapFile);
        String[] registerCommand =
                {"sh","/dspace/register-objects.sh", collectionId, source, mapFile};
        execute(registerCommand);
    }

    private void execute(String[] commands) throws InterruptedException, IOException {
        Process process = Runtime.getRuntime().exec(commands);
        InputStream errorStream = process.getErrorStream();
        setUpStreamGobbler(errorStream);
        InputStream inputStream = process.getInputStream();
        setUpStreamGobbler(inputStream);
        int exitCode = process.waitFor();
        if(exitCode != 0){
            throw new IOException("error executing DSpace import.");
        }
    }

    private static void setUpStreamGobbler(final InputStream inputStream) {
        final InputStreamReader streamReader = new InputStreamReader(inputStream);
        new Thread(() -> {
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    log.info(line);
                }
            } catch (IOException e) {
                log.warn("Unable to log stream.", e);
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
